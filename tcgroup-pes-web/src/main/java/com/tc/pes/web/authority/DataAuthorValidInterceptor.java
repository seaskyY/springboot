package com.tc.pes.web.authority;


import com.google.common.collect.Lists;
import com.tc.common.page.IPageList;
import com.tc.common.page.PageList;
import com.tc.common.security.verification.DataAuthorVerifyInterceptor;
import com.tcmc.udc.login.CasUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 数据权限service拦截器
 *
 * @author Tantal
 * @version v2.0.0
 * @date 2015/09/06 19:16
 */
public class DataAuthorValidInterceptor extends DataAuthorVerifyInterceptor {

    @SuppressWarnings("rawtypes")
    @Override
    public List assembleAfterVerifyData(Object result) {
        if (result == null) {
            return null;

        } else if (result instanceof List) {
            return (List) result;

        } else if (result instanceof IPageList) {
            return ((PageList) result).getListData();

        }
        return Lists.newArrayList(result);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getVerifyDataSet(String type) {

        return null;
    }

    @SuppressWarnings("unused")
    private UserAgent getCurUser() {
        UserAgent agent = null;
        SecurityContext secContext = SecurityContextHolder.getContext();
        Object o = secContext.getAuthentication().getPrincipal();
        if (o instanceof CasUser) {
            CasUser casUser = (CasUser) o;
            if (casUser.getUserAgent() instanceof UserAgent) {
                agent = (UserAgent) casUser.getUserAgent();
            }
        }
        if (agent == null) {
            throw new RuntimeException("Not Login!");
        }
        return agent;
    }
}
