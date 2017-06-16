
package com.tc.pes.core;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 * 
 * @author Tantal
 * @version v2.0.0
 * @date 2015/08/13 18:35
 */
public final class PesSimpleUtils {

    private static final String EMAIL_PATTERN = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    public static Hashtable<String, String> areaCode = new Hashtable<String, String>();
    static
    {

        areaCode.put("11", "北京");
        areaCode.put("12", "天津");
        areaCode.put("13", "河北");
        areaCode.put("14", "山西");
        areaCode.put("15", "内蒙古");
        areaCode.put("21", "辽宁");
        areaCode.put("22", "吉林");
        areaCode.put("23", "黑龙江");
        areaCode.put("31", "上海");
        areaCode.put("32", "江苏");
        areaCode.put("33", "浙江");
        areaCode.put("34", "安徽");
        areaCode.put("35", "福建");
        areaCode.put("36", "江西");
        areaCode.put("37", "山东");
        areaCode.put("41", "河南");
        areaCode.put("42", "湖北");
        areaCode.put("43", "湖南");
        areaCode.put("44", "广东");
        areaCode.put("45", "广西");
        areaCode.put("46", "海南");
        areaCode.put("50", "重庆");
        areaCode.put("51", "四川");
        areaCode.put("52", "贵州");
        areaCode.put("53", "云南");
        areaCode.put("54", "西藏");
        areaCode.put("61", "陕西");
        areaCode.put("62", "甘肃");
        areaCode.put("63", "青海");
        areaCode.put("64", "宁夏");
        areaCode.put("65", "新疆");
        areaCode.put("71", "台湾");
        areaCode.put("81", "香港");
        areaCode.put("82", "澳门");
        areaCode.put("91", "国外");
    }

    /**
     * int -> short 规避空指针
     *
     * @param intValue
     * @return
     */
    public static Short Integer2short(Integer intValue) {

        if (null == intValue)
            return null;
        return intValue.shortValue();
    }

    /**
     * short -> int 规避空指针
     *
     * @param shortValue
     * @return
     */
    public static Integer short2integer(Short shortValue) {

        if (null == shortValue)
            return null;
        return shortValue.intValue();
    }

    /**
     * object -> int 规避空指针
     *
     * @param obj
     * @return
     */
    public static Integer object2Integer(Object obj) {

        if (obj == null)
            return null;

        if (obj.toString().matches("\\-?[0-9]+")) {
            return Integer.valueOf(obj.toString());
        }
        return null;
    }

    public static String trimBlankToNull(String str) {

        return StringUtils.isBlank(str) ? null : str.trim();
    }

    /**
     *
     * 转化为小写，并去掉中间的空格
     *
     * @author liufei
     * @date 2016年6月22日 下午1:41:17
     *
     * @param str
     * @return
     */
    public static String trimBlankLowerCase(String str)
    {

        return StringUtils.isBlank(str) ? null : str.trim().toLowerCase().replaceAll(" ", "").replaceAll("　", "");
    }

    /**
     * 解决null+""="null"的问题
     * @return
     */
    public static String trimNullToBlank(Object object){
        if(object == null){
            return StringUtils.EMPTY;
        }
        return "" + object;
    }

    /**
     * 判断一个list是否是空list，包括null和无元素情况
     *
     * @param list
     *            需判断的list
     * @return 判断结果
     */
    public static boolean isEmpty(Collection list) {

        return null == list || list.isEmpty();
    }

    public static <T> boolean isNullOrInList(T target, List<T> lists) {

        return target == null || lists.contains(target);
    }

    public static <T> boolean isInList(T target, List<T> lists) {

        return target == null || lists.contains(target);
    }

    /**
     * 按照key归并单个元素
     *
     * @param targetMap
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public static <K, V> void putMap(Map<K, List<V>> targetMap, K key, V value) {

        Preconditions.checkNotNull(targetMap);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        if (targetMap.containsKey(key)) {
            List<V> valueList = Objects.firstNonNull(targetMap.get(key), Lists.<V>newArrayList());
            valueList.add(value);
        } else {
            targetMap.put(key, Lists.<V> newArrayList(value));
        }
    }

    /**
     * 按照key归并list
     *
     * @param targetMap
     * @param key
     * @param values
     * @param <K>
     * @param <V>
     */
    public static <K, V> void putMap(Map<K, List<V>> targetMap, K key, List<V> values) {

        Preconditions.checkNotNull(targetMap);
        Preconditions.checkNotNull(key);
        //Preconditions.checkArgument(values != null && !values.isEmpty());

        if (targetMap.containsKey(key)) {
            List<V> valueList = Objects.firstNonNull(targetMap.get(key), Lists.<V>newArrayList());
            valueList.addAll(values);
        } else {
            targetMap.put(key, Lists.<V> newArrayList(values));
        }
    }

    /**
     * 字符串转Array
     *
     * @param strs
     * @param split
     * @return
     */
    public static String[] strToArr(String strs, String split) {

        if (StringUtils.isBlank(strs))
            return null;
        return StringUtils.split(strs, split);
    }

    public static List<String> strToList(String strs, String split) {

        List<String> list = new ArrayList<>();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return list;

        for (String el : strArr) {
            list.add(el);
        }

        return list;
    }

    /**
     * 字符串转List
     *
     * @param strs
     * @param split
     * @return
     */
    public static List<Integer> strToListInt(String strs, String split) {

        List<Integer> list = new ArrayList<Integer>();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return list;

        for (String el : strArr) {
            list.add(Integer.valueOf(el));
        }

        return list;
    }

    /**
     * 字符串转List
     *
     * @param strs
     * @param split
     * @return
     */
    public static List<Short> strToListShort(String strs, String split) {

        List<Short> list = new ArrayList<Short>();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return list;

        for (String el : strArr) {
            list.add(Short.valueOf(el));
        }

        return list;
    }


    /**
     * 字符串转List
     *
     * @param strs
     * @param split
     * @return
     */
    public static List<Long> strToListLong(String strs, String split) {

        List<Long> list = new ArrayList<Long>();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return list;

        for (String el : strArr) {
            list.add(Long.valueOf(el));
        }
        return list;
    }

    /**
     * 字符串转List
     *
     * @param strs
     * @param split
     * @return
     */
    public static Set<Long> strToSetLong(String strs, String split) {
        Set<Long> set = Sets.newLinkedHashSet();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return set;

        for (String el : strArr) {
            set.add(Long.valueOf(el));
        }
        return set;
    }

    /**
     * 字符串转Set
     *
     * @param strs
     * @param split
     * @return
     */
    public static Set<Integer> strToSetInt(String strs, String split) {
        Set<Integer> set = Sets.newLinkedHashSet();
        String strArr[] = strToArr(strs, split);
        if (strArr == null)
            return set;

        for (String el : strArr) {
            set.add(Integer.valueOf(el));
        }
        return set;
    }

    /**
     * list转字符串
     *
     * @param list
     * @param split
     * @return
     */
    public static String listToStr(List list, String split) {

        if (isEmpty(list))
            return null;
        return Joiner.on(split).join(list);
    }


    public static String mapToStr(Map map, String split){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append(split).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");
        }
        return sb.toString();
    }

    public static String html(String str) {

        if (str == null) {
            return null;
        } else {
            str = str.replaceAll("\\r\\n", "<br>");
            return str;
        }
    }

    public static String escape(String input) {
        return input != null ? StringUtils.replace(input, "''", "'").replace("\"","&quot;") : input; //处理sql
    }

    /**
     * 改造org.springframework.beans.BeanUtils#copyProperties(java.lang.Object, java.lang.Object)方法，避免null属性复制
     *
     * @param source
     *            源对象
     * @param target
     *            目标对象
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
    
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }
    
    /**
     * 转换成驼峰
     * 例如：hello_word->helloWorld
     * 
     * @param source
     * @return
     */
    public static String convertCamel(String source) {
    
        StringBuilder result = new StringBuilder();
        if (source == null || source.isEmpty())
            return "";
        
        if (!source.contains("_")) {
            return source.substring(0, 1).toLowerCase() + source.substring(1);
        }
        String[] columns = source.split("_");
        for (String columnSplit : columns) {
            if (columnSplit.isEmpty()) {
                continue;
            }
            if (result.length() == 0) {
                result.append(columnSplit.toLowerCase());
            } else {
                result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
            }
        }
        return result.toString();
        
    }
    
    /**
     * 
     * 验证身份证，正确返回null，错误返回错误信息
     * 
     * @author liufei
     * @date 2016年6月13日 下午5:42:09
     * 
     * @param IDStr
     * @return
     * @throws Exception
     */
    public static String validateIDCard(String IDStr) throws Exception
    {
    
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================
        
        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================
        
        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(
                        strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
            errorInfo = "身份证生日不在有效范围。";
            return errorInfo;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================
        
        // ================ 地区码时候有效 ================
        if (areaCode.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================
        
        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        
        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return null;
        }
        // =====================(end)=====================
        return null;
    }
    
    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     * 
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str) {
    
        boolean flag = false;
        // String regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }
    
    /**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
    
        if (str == null || "".equals(str))
            return Boolean.FALSE;
        
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
    
    /**
     * 
     * 验证EMAIL
     * 
     * @author liufei
     * @date 2016年6月13日 下午5:50:34
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email)
    {
    
        if (null == email || "".equals(email))
            return false;
        
        Pattern p = Pattern.compile(EMAIL_PATTERN);// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
        
    }

    public static String getSexStr(Integer sex) {
        if (null == sex) return "";
        switch (sex) {
            case 0:
                return "女";
            case 1:
                return "男";
            default:
                return "";
        }
    }

    public static String getLinuxFile(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) return null;
        return fileUrl.replace("\\", "/");
    }

    public static int formatInt(BigDecimal doubleValue) {
        if (null==doubleValue) return 0;
        return doubleValue.intValue();
    }

    public static String formatIntToStr(Integer intValue) {
        if (null==intValue) return "";
        DecimalFormat df=new DecimalFormat("0000");
        return df.format(intValue);
    }

    /**
     * 获取最大单价
     * @param denary
     * @param minMaterialPrice
     * @return
     */
    public static BigDecimal getMaxMaterialPrice(Integer denary, BigDecimal minMaterialPrice){
        if(null == denary || denary <= 0){
            return minMaterialPrice.setScale(4, BigDecimal.ROUND_HALF_UP);
        }else{
            return minMaterialPrice.multiply(new BigDecimal(denary)).setScale(4, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 获取最大单位的数量
     * @param denary
     * @param num
     * @return
     */
    public static BigDecimal getNumOfMaxUnit(Integer denary, BigDecimal num){
        if(denary == null || denary < 0){
            return num.setScale(2, BigDecimal.ROUND_HALF_UP);
        }else{
            return num.divide(new BigDecimal(denary), 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 获取最大单位
     * @param maxUnit
     * @param materialUnit
     * @return
     */
    public static String getUnit(String maxUnit,String materialUnit){
        if(null != maxUnit){
            return maxUnit;
        }else {
            return materialUnit;
        }
    }



}
