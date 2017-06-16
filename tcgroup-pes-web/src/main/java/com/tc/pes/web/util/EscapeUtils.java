package com.tc.pes.web.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.LookupTranslator;

/**
 * description
 *
 * @author Tantal
 * @version v2.0.0
 * @date 2015/08/19 14:59
 */
public final class EscapeUtils {

    private static final CharSequenceTranslator ESCAPE_HTML = new LookupTranslator(new String[][]{{"<", "&lt;"}, {">", "&gt;"}, {"\"", "&quot;"}});


    /**
     * 使HTML的标签失去作用
     *
     * @param input 被操作的字符串
     * @return String
     */
    public static String escapeHTMLTag(String input) {

        return StringUtils.isBlank(input) ? input : ESCAPE_HTML.translate(input); // 处理html
    }

    public static String escapeSql(String input) {
        return input != null ? StringUtils.replace(input, "'", "''") : input; // 处理sql
    }

    /**
     * 处理<script> </script>代码 标签只有<script></script>有效,而<sc ript></script>标签无效
     * @param input
     * @return
     */
    public static String escapeScript(String input){
        if (input.contains("script") && input.contains("<") && input.contains(">")){
            input = EscapeUtils.escapeHTMLTag(input);
        }
        return input;
    }

    /**
     * 处理参数，防止html、sql注入
     *
     * @param str
     * @return
     */
    public static String escapeHtmlOrSql(String str) {

        // 先处理html
        str = StringUtils.isNotBlank(str) ? escapeHTMLTag(str
                .trim()) : null;
        // //再处理sql
        str = StringUtils.isNotBlank(str) ? escapeSql(str.trim()) : null;
        return str;
    }

}
