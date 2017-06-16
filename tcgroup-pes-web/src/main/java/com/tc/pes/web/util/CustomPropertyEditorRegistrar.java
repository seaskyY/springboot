package com.tc.pes.web.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 实现spring PropertyEditorRegistrar接口，处理自定义格式日期和脚本注入攻击
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    private static final Pattern ptnYM = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\s*$");
    private static final Pattern ptnYMD = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s*$");
    private static final Pattern ptnYMDHM = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s+([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]\\s*$");
    private static final Pattern ptnYMDHMS = Pattern.compile("^\\s*[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[1-2][0-9]|3[0-1])\\s+([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]\\s*$");
    private static final DateFormat dfYM = new SimpleDateFormat("yyyy-MM");
    private static final DateFormat dfYMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat dfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat dfYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (value != null) {
                    try {
                        if (ptnYMDHMS.matcher(value).find())
                            setValue(dfYMDHMS.parse(value));
                        else if (ptnYMDHM.matcher(value).find())
                            setValue(dfYMDHM.parse(value));
                        else if (ptnYMD.matcher(value).find())
                            setValue(dfYMD.parse(value));
                        else if(ptnYM.matcher(value).find())
                            setValue(dfYM.parse(value));
                        else
                            setValue(null);
                    } catch (ParseException e) {
                        setValue(null);
                    }
                } else {
                    setValue(null);
                }
            }

            public String getAsText() {
                if (getValue() != null)
                    return dfYMDHMS.format((Date) getValue());
                return null;
            }
        });


        /**
         * 处理XSS攻击、SQL HTML脚本注入
         */
        registry.registerCustomEditor(String.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtils.isBlank(value)) {
                    setValue(null);                      //空白则转null，并且不进行转义
                    return;
                }
                value = StringUtils.trim(value);                //使用escapeHtml和escapeJavascript会导致中文出现问题
                value = EscapeUtils.escapeSql(value); //处理sql
                value = EscapeUtils.escapeScript(value);//处理script

                setValue(value);
            }
        });
    }
}
