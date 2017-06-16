
package com.tc.pes.web.resolver;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author liufei 2015年8月13日
 */

public class SessionConditionInterceptor implements WebRequestInterceptor {

    private ConditionUrl conditionUrl;

    public void setConditionUrl(ConditionUrl conditionUrl) {
        this.conditionUrl = conditionUrl;
    }

    @Override
    public void preHandle(WebRequest request) throws Exception {

        // TODO Auto-generated method stub
        /**
         * @author liufei 2015年8月13日
         */

    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

        //当前的url
        String url = "";
        if(request instanceof ServletWebRequest){
            url = ((ServletWebRequest) request).getRequest().getServletPath();
        }
        //条件暂存key
        String conditionKey = conditionUrl.getKey(url);

        if (model != null) {
            for (Object el : model.values()) {
                if (el != null) {
                    if (SessionConditionResolver.getClassList().contains(el.getClass().getSuperclass())
                            || SessionConditionResolver.getClassList().contains(el.getClass())) {
                        request.setAttribute(conditionKey,
                                el, WebRequest.SCOPE_SESSION);
                    }
                }
            }
        }
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

        // TODO Auto-generated method stub
        /**
         * @author liufei 2015年8月13日
         */

    }

}
