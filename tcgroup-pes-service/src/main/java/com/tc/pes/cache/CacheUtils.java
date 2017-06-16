package com.tc.pes.cache;


import com.tcmc.cache.CacheKeyGeneratorV3;

public class CacheUtils {

    /**
     * 带系统名的KEY转化为 0-系统名 1-不带系统名的KEY
     *
     * @param sourceKey
     * @return 0-系统名 1-不带系统名的KEY
     * @author liufei
     * @date 2015年7月31日 上午9:47:47
     */
    public static String[] sourceKeyToKey(String sourceKey) {

        String[] strs = new String[2];
        int ind = sourceKey.indexOf(CacheKeyGeneratorV3.SYSTEM_SPLIT);
        if (ind == -1)
            strs[1] = sourceKey;
        else {
            strs[0] = sourceKey.substring(0, ind);

            // 删除key的分组前缀
            int ind2 = strs[0].indexOf(CacheKeyGeneratorV3.PREFIX_SPLIT);
            if (ind2 >= 0) {
                strs[0] = strs[0].substring(ind2 + CacheKeyGeneratorV3.PREFIX_SPLIT.length(), strs[0].length());
            }

            strs[1] = sourceKey.substring(ind + CacheKeyGeneratorV3.SYSTEM_SPLIT.length(), sourceKey.length());
        }
        return strs;
    }
}
