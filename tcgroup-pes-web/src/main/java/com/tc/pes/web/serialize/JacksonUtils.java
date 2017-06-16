
package com.tc.pes.web.serialize;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json解析工具
 * Created by tantao on 14-9-24.
 */
public final class JacksonUtils {

    private final static ObjectMapper objMap = new ObjectMapper();

    //json 转换Bean, Bean 里没有对应的值 jackson Unrecognized field 异常处理
    static {
//        objMap.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objMap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final static ObjectMapper objMapNonNull =
            new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * 将字符串格式的json数据转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2Map(String json) {

        try {
            return objMap.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    /**
     * 将map对象转换成字符串格式的json数据
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, Object> map) {

        try {
            return objMap.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String entity2Json(T obj) {

        String json = null;
        try {
            json = objMap.writeValueAsString(obj);// 注意：日期在json数据中是long格式数据
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T json2Entity(String json, Class<T> clazz) {
        byte[] bJson = null;
        try {
            bJson = json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return json2Entity(bJson, clazz);
    }

    public static <T> T json2Entity(byte[] bytes, Class<T> clazz) {

        T obj = null;
        try {
            obj = objMap.readValue(bytes, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 为NULL的属性不序列化
     *
     * @param obj
     * @return
     * @author liufei
     * @date 2015年4月27日 下午2:18:57
     */
    public static <T> String entity2JsonNonNull(T obj) {

        String json = null;
        try {
            json = objMapNonNull.writeValueAsString(obj);// 注意：日期在json数据中是long格式数据
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> String list2Json(List<T> list) {

        String json = null;
        try {
            json = objMap.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> List<T> json2List(String json, Class<T[]> clazz) {

        T[] objs = null;
        try {
            objs = objMap.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(objs);
    }
}
