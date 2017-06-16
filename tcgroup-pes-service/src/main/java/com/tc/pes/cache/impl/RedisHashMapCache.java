
package com.tc.pes.cache.impl;

import com.google.common.collect.Sets;
import com.tc.pes.cache.CacheUtils;
import com.tc.pes.cache.ICompactCache;
import com.tcmc.cache.CustomerKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

/**
 * redis缓存
 *
 * @author liufei 2015年2月2日
 */
public class RedisHashMapCache implements ICompactCache {

    final Logger logger = LoggerFactory.getLogger(RedisHashMapCache.class);

    private static final long DEFAULT_EXPIRY = 60 * 1000 * 30;

    private RedisTemplate<byte[], byte[]> redisTemplate;

    public void setRedisTemplate(RedisTemplate<byte[], byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private CustomerKey keyGenerator;

    public void setKeyGenerator(CustomerKey keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public void put(String key, Object value, Date expiry) {

        if (expiry == null)
            expiry = new Date(new Date().getTime() + DEFAULT_EXPIRY);// 默认30分钟

        this.save(key, value, expiry);
    }

    @Override
    public Object get(String key) {

        return this.find(key);
    }

    @Override
    public Set<String> getAllKeys(final String pattern) {

        @SuppressWarnings("unchecked")
        Set<byte[]> bKeys = (Set<byte[]>) redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {

                return connection.keys(pattern.getBytes());
            }
        });

        Set<String> set = Sets.newHashSet();
        for (byte[] bKey : bKeys) {
            set.add(CacheUtils.sourceKeyToKey(new String(bKey))[1]);
        }
        return set;
    }


    private byte[] getKeyBytes(final String key) {
        if (StringUtils.isBlank(key)) {
            logger.error("缓存key不能为空");
            throw new RuntimeException("缓存key不能为空");
        }
        return keyGenerator.getCacheKey(key).getBytes(Charset.forName("UTF8"));
    }

    /**
     * 保存REDIS缓存，value为空，删除缓存
     * 如果设置过期时间小于当前时间,默认60秒后过期
     *
     * @param key
     * @param value
     * @param expiry
     * @author liufei
     * @date 2015年2月4日 上午10:29:03
     */
    private void save(final String key, final Object value, final Date expiry) {

        if (key == null) {
            logger.warn("缓存KEY为 null.");
            return;
        }

        if (expiry == null) {
            logger.warn("缓存有效时间为 null.");
            return;
        }

        try {
            redisTemplate.execute(new RedisCallback<Object>() {

                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {

                    byte[] keyBytes = getKeyBytes(key);
                    if (value == null)
                        connection.del(keyBytes);
                    else {
                        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                        connection.set(keyBytes, valueSerializer.serialize(value));
                        boolean rs = connection.expireAt(keyBytes, expiry.getTime() / 1000);

                        if (!rs)
                            logger.warn(key + "缓存有效时间设置失败");
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("保存READIS缓存异常:" + e.getMessage());
        }
    }

    /**
     * 通过KEY，查找REDIS缓存
     *
     * @param key
     * @return
     * @author liufei
     * @date 2015年2月4日 上午10:29:11
     */
    private Object find(final String key) {

        if (key == null) {
            logger.warn("缓存KEY为 null.");
            return null;
        }

        try {
            return redisTemplate.execute(new RedisCallback<Object>() {

                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {

                    byte[] bytes = connection.get(getKeyBytes(key));
                    if (bytes == null)
                        return null;
                    else {
                        return redisTemplate.getValueSerializer().deserialize(bytes);
                    }
                }
            });
        } catch (Exception e) {
            logger.error("得到READIS缓存异常:" + e.getMessage());
            return null;
        }
    }

    @Override
    public void clear(String key) {
        this.put(key, null, null);
    }

    @Override
    public void clearE(final String key) {

        redisTemplate.execute(new RedisCallback<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.del(getKeyBytes(key));
                return null;
            }
        });
    }
}