
package com.tc.pes.cache.impl;

import com.tc.pes.cache.ICompactCache;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存
 */
public class LocalHashMapCache implements ICompactCache {

    private final static Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    public Object get(String key) {

        Object o = map.get(key);
        if (null != o) {
            @SuppressWarnings("unchecked")
            CacheInner oT = (CacheInner) o;

            if (oT.getDt().compareTo(new Date()) <= 0) {// 已经过期
                put(key, null, null);
                return null;
            }
            return oT.getInnerValue();
        }
        return null;
    }

    @Override
    public Set<String> getAllKeys(String pattern) {
        return null;
    }

    public void put(String key, Object value, Date expiry) {

        if (value == null) {
            map.remove(key);
        } else {
            map.put(key, new CacheInner(value, expiry));
        }
    }

    // 通过一个内部类，模拟lazy模式管理缓存
    private class CacheInner {

        private final Object innerValue;
        private final Date dt;

        public Date getDt() {

            return this.dt;
        }

        public CacheInner(Object o, Date expiry) {

            this.innerValue = o;
            if (null == expiry) {
                // 默认缓存3分钟
                this.dt = new Date(new Date().getTime() + 720000);
            } else {
                this.dt = expiry;
            }
        }

        public Object getInnerValue() {

            return innerValue;
        }
    }

    @Override
    public void clear(String key) {
        this.put(key, null, null);
    }

    @Override
    public void clearE(String key) {

    }
}
