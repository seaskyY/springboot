package com.tc.pes.web.resolver;

import com.tc.common.url.URLBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * action通用异常处理
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    private String errorPage = "error";

    //设置当前操作核算分类的视图
    private String setCurAccountClassIdView="/common/setClassId";

    private String defaultReturnUrl;

    private final String message_tag = "message_tag";
    private final String return_url_tag = "return_url_tag";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) {
        //交给spring security 处理
        /*if (ex instanceof AccessDeniedException) {
            return null;
        }*/

        logger.warn("catch one exception:" + getRequestUrlMethodParameter(request), ex);

        return new ModelAndView(this.errorPage).addObject(message_tag, ex.getMessage()).addObject(return_url_tag, appServerBroker.get(getDefaultReturnUrl()));
    }

    /**
     * 获取请求的url地址,请求方式和请求参数
     *
     * @param request
     * @return
     */
    private String getRequestUrlMethodParameter(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();
        StringBuilder sbParameters = new StringBuilder();
        sbParameters.append("request Url:").append(request.getRequestURI()).append("|");
        sbParameters.append("method:").append(request.getMethod()).append("|");
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            sbParameters.append(paramName).append(":");
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues != null) {
                for (int i = 0; i < paramValues.length; i++) {
                    sbParameters.append(paramValues[i]).append(",");
                }
                sbParameters.append("|");
            } else {
                sbParameters.append("null|");
            }
        }
        return sbParameters.toString();
    }

    @Resource
    private URLBroker appServerBroker;

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    public String getDefaultReturnUrl() {
        return defaultReturnUrl;
    }

    public void setDefaultReturnUrl(String defaultReturnUrl) {
        this.defaultReturnUrl = defaultReturnUrl;
    }

    public String getSetCurAccountClassIdView() {
        return setCurAccountClassIdView;
    }

    public void setSetCurAccountClassIdView(String setCurAccountClassIdView) {
        this.setCurAccountClassIdView = setCurAccountClassIdView;
    }
}
