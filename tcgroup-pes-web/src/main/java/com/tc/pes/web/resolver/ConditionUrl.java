package com.tc.pes.web.resolver;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * ConditionUrl
 *
 * @author zhangshaojiao
 * @version v2.0.0
 * @date 2016/8/26 14:32
 */
public class ConditionUrl {

    private static final String DEFAULT_CONDITION_SESSION_KEY = "SCM_CDT_SN_NAME";

    /**
     * url -> session key的映射关系
     */
    private Map<String, Integer> urlConditionKeyMap;

    public Map<String, Integer> getUrlConditionKeyMap() {
        return urlConditionKeyMap;
    }

    public void setUrlConditionKeyMap(Map<String, Integer> urlConditionKeyMap) {
        this.urlConditionKeyMap = urlConditionKeyMap;
    }

    /**
     * 不存在的时候返回空字符串
     * @param url
     * @return
     */
    public String getKey(String url){
        if(StringUtils.isNotBlank(url)){
            int position = url.lastIndexOf("/");
            if(position > 0){
                String keyTp = url.substring(0, position + 1);
                if(urlConditionKeyMap.keySet().contains(keyTp)){
                    return DEFAULT_CONDITION_SESSION_KEY + urlConditionKeyMap.get(keyTp).toString();
                }
            }
        }
        return DEFAULT_CONDITION_SESSION_KEY;
    }

}
