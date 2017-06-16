
package com.tc.pes.web.resolver;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.security.util.FieldUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author liufei 2015年8月11日
 */

public class SessionConditionResolver implements HandlerMethodArgumentResolver {
    private static final Pattern ptnYM = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\s*$");
    private static final Pattern ptnYMD = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s*$");
    private static final Pattern ptnYMDHM = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s+([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]\\s*$");
    private static final Pattern ptnYMDHMS = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s+([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]\\s*$");
    private static final DateFormat dfYM = new SimpleDateFormat("yyyy-MM");
    private static final DateFormat dfYMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat dfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat dfYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected static List<Class<?>> classList = Lists.newArrayList();

    private ConditionUrl conditionUrl;

    public void setConditionUrl(ConditionUrl conditionUrl) {
        this.conditionUrl = conditionUrl;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return classList.contains(parameter.getParameterType().getSuperclass())
                || classList.contains(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //当前的url
        String url = "";
        if(webRequest instanceof ServletWebRequest){
            url = ((ServletWebRequest) webRequest).getRequest().getServletPath();
        }
        //条件暂存key
        String conditionKey = conditionUrl.getKey(url);

        Object condition = webRequest.getAttribute(conditionKey, RequestAttributes.SCOPE_SESSION);
        if (condition == null || !parameter.getParameterType().equals(condition.getClass()))
            condition = parameter.getParameterType().newInstance();

        this.fillCondition(condition, webRequest.getParameterMap());
        return condition;

    }

    /**
     * 填充condition对象
     *
     * @param condition
     * @param paramMap
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author liufei
     * @date 2015年8月12日 下午8:42:09
     */
    public void fillCondition(Object condition, Map<String, String[]> paramMap) throws ParseException, IllegalAccessException, InvocationTargetException {

        Class<? extends Object> clazz = condition.getClass();
        Field field = null;
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            try {
                field = FieldUtils.getField(clazz, param.getKey());// 如果FIELDNAME不存在，进入异常，continue
            } catch (IllegalStateException e) {
                continue;
            }

            if (param.getValue() == null)
                continue;

            if (field.getType().isArray())// 生成数组
                this.parseArray(field, condition, param.getKey(), param.getValue(),
                        field.getType().getComponentType());
            else if (List.class.equals(field.getType()) ||
                    ArrayUtils.contains(field.getType().getInterfaces(), List.class))// 生成LIST
            {
                this.parseList(field, condition, param.getKey(), param.getValue(),
                        this.getActualTypeFieldType(field));
            } else if (Set.class.equals(field.getType()) ||
                    ArrayUtils.contains(field.getType().getInterfaces(), Set.class))// 生成SET
            {
                this.parseSet(field, condition, param.getKey(), param.getValue(),
                        this.getActualTypeFieldType(field));
            } else
                // 普通类型
                this.parseValue(field, condition, param.getKey(), param.getValue(), field.getType());
        }
    }

    /**
     * LIST,SET得到泛型类型，如果没有指定泛型或有多个泛型存在，返回NULL
     *
     * @param field
     * @return
     * @author liufei
     * @date 2015年8月12日 下午8:44:28
     */
    private Type getActualTypeFieldType(Field field) {

        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length == 1)
                return parameterizedType.getActualTypeArguments()[0];
            else
                return null;
        } else
            return null;
    }

    /**
     * 放入单对象值
     *
     * @param field
     * @param condition
     * @param fieldName
     * @param values
     * @param elType
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     * @author liufei
     * @date 2015年8月12日 下午8:45:19
     */
    @SuppressWarnings("rawtypes")
    private void parseValue(Field field, Object condition, String fieldName,
                            String[] values, Class elType) throws IllegalAccessException, ParseException {

        if (values.length == 1) {
            if (values[0] == null)
                return;

            Object value = this.getValue(elType, values[0]);

            setPropertie(field, condition, value);
        }
    }

    /**
     * 放入数组对象值
     *
     * @param field
     * @param condition
     * @param fieldName
     * @param values
     * @param elType
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author liufei
     * @date 2015年8月12日 下午8:45:42
     */
    @SuppressWarnings({"rawtypes"})
    private void parseArray(Field field, Object condition, String fieldName,
                            String[] values, Class elType) throws ParseException, IllegalAccessException {

        int len = values.length;
        Object array = Array.newInstance(elType, len);
        Object value;
        for (int i = 0; i < len; i++) {
            if (values[i] == null)
                continue;

            value = this.getValue(elType, values[i]);
            Array.set(array, i, value);
        }

        setPropertie(field, condition, array);
    }

    /**
     * 放入LIST对象值
     *
     * @param field
     * @param condition
     * @param fieldName
     * @param values
     * @param elType
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author liufei
     * @date 2015年8月12日 下午8:45:57
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void parseList(Field field, Object condition, String fieldName,
                           String[] values, Type elType) throws ParseException, IllegalAccessException, InvocationTargetException {

        List list = Lists.newArrayList();
        for (String value : values) {
            if (value == null)
                continue;
            list.add(this.getValue(elType, value));
        }
        BeanUtils.setProperty(condition, fieldName, list);
    }

    /**
     * 放入SET对象值
     *
     * @param field     ;
     * @param condition
     * @param fieldName
     * @param values
     * @param elType
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author liufei
     * @date 2015年8月12日 下午8:46:15
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void parseSet(Field field, Object condition, String fieldName,
                          String[] values, Type elType) throws ParseException, IllegalAccessException, InvocationTargetException {

        Set set = Sets.newHashSet();
        for (String value : values) {
            if (value == null)
                continue;
            set.add(this.getValue(elType, value));
        }
        BeanUtils.setProperty(condition, fieldName, set);
    }

    /**
     * String转化为Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     * @author liufei
     * @date 2015年8月12日 下午3:19:49
     */
    private Date stringToDate(String strDate) throws ParseException {

        if (ptnYMDHMS.matcher(strDate).find())
            return dfYMDHMS.parse(strDate);
        else if (ptnYMDHM.matcher(strDate).find())
            return dfYMDHM.parse(strDate);
        else if (ptnYMD.matcher(strDate).find())
            return dfYMD.parse(strDate);
        else if (ptnYM.matcher(strDate).find())
            return dfYM.parse(strDate);
        else
            throw new ParseException("Session Condition String " + strDate + " parse Date Error", -1);
    }

    protected static List<Class<?>> getClassList() {

        return classList;
    }

    public void setClassList(List<Object> classList) {

        if (classList != null) {
            for (Object object : classList) {
                SessionConditionResolver.classList.add(object.getClass());
            }
        }
    }

    /**
     * 根据类型判断取值
     *
     * @param elType
     * @param inValue
     * @return
     * @throws ParseException
     * @author liufei
     * @date 2015年8月17日 下午7:50:39
     */
    private Object getValue(Type elType, String inValue) throws ParseException {

        if (elType.equals(String.class))
            return inValue;
        else if (elType.equals(Integer.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Integer.valueOf(inValue).intValue();
        } else if (elType.equals(int.class)) {
            if (inValue.isEmpty())
                return 0;
            else
                return Integer.valueOf(inValue).intValue();
        } else if (elType.equals(Double.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Double.valueOf(inValue).doubleValue();
        } else if (elType.equals(double.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Double.valueOf(inValue).doubleValue();
        } else if (elType.equals(Long.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Long.valueOf(inValue).longValue();
        } else if (elType.equals(long.class)) {
            if (inValue.isEmpty())
                return 0;
            else
                return Long.valueOf(inValue).longValue();
        } else if (elType.equals(Short.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Short.valueOf(inValue).shortValue();
        }else if (elType.equals(Date.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return stringToDate(inValue);
        } else if (elType.equals(BigDecimal.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return new BigDecimal(inValue);
        } else if (elType.equals(Boolean.class)) {
            if (inValue.isEmpty())
                return null;
            else
                return Boolean.valueOf(inValue).booleanValue();
        } else if (elType.equals(boolean.class)) {
            return !inValue.isEmpty() && Boolean.valueOf(inValue).booleanValue();
        } else
            throw new RuntimeException("codition中存在不支持的属性类型");
    }

    /**
     * 设置属性 APACHE的包不能设置为NULL，所以使用这个
     *
     * @param field
     * @param condition
     * @param value
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @author liufei
     * @date 2015年8月17日 下午7:32:06
     */
    private void setPropertie(Field field, Object condition, Object value) throws IllegalArgumentException, IllegalAccessException {

        field.setAccessible(true);
        field.set(condition, value);
    }
}
