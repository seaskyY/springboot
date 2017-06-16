
package com.tc.pes.web.util;

import java.lang.annotation.*;

/**
 * 防止重复提交注解，用于请求页面的处理方法。
 * <p>在初始化页面方法上，设置needSaveToken()为true，此时拦截器会在Session中保存一个token，
 * 同时需要在新建的页面表单中添加${formToken}变量生成隐藏域。
 * <p>请求的提交处理方法需要验证重复提交的，设置needRemoveToken为true，此时会在拦截器中验证是否重复提交。
 *
 * @author vinfer
 * @date 2014-1-16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AvoidFormReSubmission {

    /**
     * 标记是否生成并在服务端保存token。
     *
     * @return
     * @author jiaomingyang
     * @version v2.0.9
     * @date 2014年7月10日 下午3:14:50
     */
    boolean needSaveToken() default false;

    /**
     * 标记方法是否校验并移除token。
     *
     * @return
     * @author jiaomingyang
     * @version v2.0.9
     * @date 2014年7月10日 下午3:14:14
     */
    boolean needRemoveToken() default false;
}
