package com.tc.pes.cache;

import java.util.Date;
import java.util.Set;

/**
 * 缓存接口
 */
public interface ICompactCache {
    /**
     * 加入缓存时,设置超时的时间
     */
    public void put(String key, Object value, Date expiry);

    /**
     * 从cache中得到key对应的值
     *
     * @param key 不能为null
     * @return 如果cache不存在，或者已经过期，则返回null
     */
    public Object get(String key);

    /**
     * 根据条件字符串模糊匹配得到所有KEY
     *
     * @param pattern
     * @author liufei
     * @date 2015年7月31日 下午4:41:26
     */
    public Set<String> getAllKeys(String pattern);

    /**
     * put null方式删除缓存
     *
     * @param key
     * @author liufei
     * @date 2015年4月24日 上午10:48:53
     */
    public void clear(String key);

    /**
     * del value方式删除缓存
     *
     * @param key
     */
    public void clearE(final String key);

}
