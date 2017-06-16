package com.tc.pes.web.resolver;

import com.tc.pes.web.authority.UserAgent;
import com.tcmc.udc.login.CasUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * UserAgent数据组装
 *
 * @author Tantal
 * @date 2016-5-31
 */
public class MemberAgentArgumentResolver implements HandlerMethodArgumentResolver {

    private CasUser getCurUser() {
        SecurityContext secContext = SecurityContextHolder.getContext();
        Object o = secContext.getAuthentication().getPrincipal();
        if (o instanceof CasUser)
            return (CasUser) o;
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserAgent.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserAgent member=null;
        //直接从cas用户信息中获取
        CasUser innerUser = getCurUser();
        if(innerUser != null){
            if(innerUser.getUserAgent() instanceof UserAgent) {
                member = (UserAgent)innerUser.getUserAgent();
            }
        }
        return member;
    }


}
