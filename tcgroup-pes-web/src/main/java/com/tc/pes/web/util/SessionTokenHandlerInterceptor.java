
package com.tc.pes.web.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 分布式环境下，防止表单提交的拦截器。
 * 通过拦截添加{@link AvoidFormReSubmission @AvoidFormReSubmission}注解的请求处理方法，处理表单的重复提交。
 *
 * @author jiaomingyang
 * @version v2.0.9
 * @date 2014年7月10日 下午2:45:29
 * @id $Id: SessionTokenHandlerInterceptor.java 10 2016-05-31 12:55:30Z tantao $
 */
public class SessionTokenHandlerInterceptor implements HandlerInterceptor {

    private static final Log logger = LogFactory.getLog(SessionTokenHandlerInterceptor.class);

    private RedisTemplate<byte[], byte[]> redisTokenTemplate;

    public void setRedisTokenTemplate(RedisTemplate<byte[], byte[]> redisTokenTemplate) {
        this.redisTokenTemplate = redisTokenTemplate;
    }

    // 表单名称
    private final String formToken = "formToken";
    // 表单名称
    private final String urlToken = "urlToken";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        AvoidFormReSubmission an = getAnnotation(handler);
        if (an == null) return true;//没有注解，放行
        if (an.needSaveToken()) {
            setRedis(generateSessionToken(request));
        }
        if (an.needRemoveToken()) {
            String token = request.getParameter(formToken);
            if (token != null && checkRedis(token) > 0) {
                return true;
            }
            String msg = request.getRemoteHost() + request.getRequestURI() + "请求的来源页面已经失效";
            logger.warn(msg);
            throw new Exception(msg);
        }
        return true;
    }

    /**
     * 生成唯一token，并设置为request属性。
     *
     * @return
     */
    private String generateSessionToken(HttpServletRequest request) {
        String token = RandomStringUtils.randomAlphanumeric(32);
        request.setAttribute(formToken, "<input type=\"hidden\" id=\"" + formToken + "\" name=\"" + formToken + "\" value=\"" + token + "\"/>");
        request.setAttribute(urlToken, formToken + "=" + token);
        return token;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //什么也不做
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //什么也不做
    }

    private AvoidFormReSubmission getAnnotation(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            return hm.getMethodAnnotation(AvoidFormReSubmission.class);
        }
        return null;
    }

    /**
     * 删除key并返回被删除 key 的数量。
     *
     * @param key
     * @return 如果key为null，返回null，否则返回被删除 key 的数量。
     */
    private Long checkRedis(final String key) {
        if (key == null) return null;
        return redisTokenTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(key.getBytes());
            }

        });
    }

    /**
     * 删除key并返回被删除 key 的数量。
     *
     * @param key
     * @return 如果key为null，返回null，否则返回被删除 key 的数量。
     */
    private void setRedis(final String key) {
        if (key == null) return;
        redisTokenTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyBytes = key.getBytes();
                connection.set(keyBytes, keyBytes);
                connection.expireAt(keyBytes, (new Date().getTime() + 24 * 60 * 60 * 1000) / 1000);//24小时过期
                return null;
            }

        });
    }
}
