
package com.tc.pes.web.authority;

import com.tcmc.udc.login.CasUserAgent;
import com.tcmc.udc.login.DefaultUserDetailsServiceImpl;
import com.tcmc.udc.login.HospitalGrantedAuthority;
import com.tcmc.udc.login.ParentHospitalGrantedAuthority;
import com.tcmc.udc.pom.api.model.LoginInfo;
import com.tcmc.udc.pom.api.model.MenuModel;
import com.tcmc.udc.pom.api.model.MenuPrivilegeModel;
import com.tcmc.udc.pom.api.serivce.IDataPrivilegesDpiService;
import com.tcmc.udc.pom.constantvar.enums.ExceptionEnums;
import com.tcmc.udc.pom.constantvar.exception.PomBusinessException;
import com.tcmc.udc.pom.constantvar.exception.PomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * 用户身份认证
 */
public class UserDetailsServiceImpl extends DefaultUserDetailsServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private IDataPrivilegesDpiService dataPrivilegesDpiService;
    private String productCode;

    public void setProductCode(String productCode) {
        this.productCode = productCode;
		List<String> productCodeLs = new ArrayList<>();
		if(null !=productCode){
			String[] arr = productCode.split(",");
			if(null !=arr){
				productCodeLs = Arrays.asList(arr);
			}
		}
		super.setProductCodes(productCodeLs);
    }

    public IDataPrivilegesDpiService getDataPrivilegesDpiService() {

        return dataPrivilegesDpiService;
    }

    public void setDataPrivilegesDpiService(IDataPrivilegesDpiService dataPrivilegesDpiService) {

        this.dataPrivilegesDpiService = dataPrivilegesDpiService;
    }

    @Override
    protected Set<String> getMockPrivilegeCodes() {

        // //权限mock
        // Set<String> autSets = Sets.newHashSet();
        // autSets.add(HbdAuthorityConstant.HBD_CASEMGR);
        // autSets.add(HbdAuthorityConstant.HBD_PARTNER);
        // autSets.add(HbdAuthorityConstant.HBD_PATIENTCASE);
        //
        // return autSets;

        return Collections.EMPTY_SET;
    }

    @Override
    protected CasUserAgent constructUserAgent(Long accountId, Long staffId, String userName,
                                              Long loginHospitalId, Long parentHospitalId, Long officeId,
                                              String parentHospitalName, String loginHospitalName, String officeName,
                                              Map<Long, MenuModel> allMenuMap,
                                              List<MenuPrivilegeModel> menuPrivileges) throws PomException {

        UserAgent agent = new UserAgent(accountId, staffId, userName, loginHospitalId, parentHospitalId, officeId);
        agent.setLoginHospitalName(loginHospitalName);
        agent.setParentHospitalName(parentHospitalName);
        agent.setOfficeName(officeName);
        // 构建MENU MAP
        if (menuPrivileges != null && !menuPrivileges.isEmpty())
            agent.setMenuMap(this.getMenus(allMenuMap, menuPrivileges));

        //1 设置数据范围
        //this.fillDataScopeRange(agent);

        //2 设置userAgent获取数据权限
        List<GrantedAuthority> dataGrantedAuthorityList;
        try {
            dataGrantedAuthorityList = getDataGrantedAuthority(accountId, staffId, parentHospitalId, loginHospitalId);
        } catch (Exception e) {
            logger.warn(String.format("获取数据权限异常,accountId:%d, productCode:%s, errMsg:%s", accountId, productCode, e.getMessage()));
            throw new PomBusinessException(ExceptionEnums.ExceptionCodes.API_ERROR, "得到数据权限错误", e);
        }
        if (dataGrantedAuthorityList != null && dataGrantedAuthorityList.size() > 0) {
            for (GrantedAuthority gat : dataGrantedAuthorityList) {
                if (gat instanceof ParentHospitalGrantedAuthority) {
                    agent.setParentHospitalIds(gat.getAuthority());
                } else if (gat instanceof HospitalGrantedAuthority) {
                    agent.setHospitalIds(gat.getAuthority());
                }
            }
        }

        return agent;
    }


    @Override
    protected List<GrantedAuthority> getDataGrantedAuthority(Long accountId, Long staffId, Long parentHospitalId, Long hospitalId) throws PomException {
            List<GrantedAuthority> retGAs = new ArrayList<>();

        //数据范围控制(权限)暂时不使用
            /*Set<Long> parentHospitalIdSet = new HashSet<>();
            Set<Long> hospitalIdSet = new HashSet<>();

            // 至少有当前登录分院的权限
            parentHospitalIdSet.add(parentHospitalId);
            hospitalIdSet.add(hospitalId);

            List<DataPrivilege> dataPrivilegeList = dataPrivilegesDpiService.queryDataPrivilegesByAccountId(accountId, productCode);
            if (dataPrivilegeList != null && dataPrivilegeList.size() > 0) {
                for (DataPrivilege tpDp : dataPrivilegeList) {
                    parentHospitalIdSet.add(tpDp.getParentHospitalId());
                    hospitalIdSet.add(tpDp.getHospitalId());
                }
            }
            retGAs.add(new ParentHospitalGrantedAuthority(parentHospitalIdSet));// 总院集合
            retGAs.add(new HospitalGrantedAuthority(hospitalIdSet));// 分院集合*/

            return retGAs;
    }

    /**
     * 得到菜单
     *
     * @param allMenuMap
     * @param menuPrivileges
     * @return  key为菜单id， value为下级菜单的id列表
     * @author liufei
     * @date 2016年6月16日 下午6:59:05
     */
    public Map<Long, List<Long>> getMenus(Map<Long, MenuModel> allMenuMap, List<MenuPrivilegeModel> menuPrivileges) {
        // 排序
        Collections.sort(menuPrivileges, new Comparator<MenuPrivilegeModel>() {

            @Override
            public int compare(MenuPrivilegeModel o1, MenuPrivilegeModel o2) {

                return o1.getOrderNo().compareTo(o2.getOrderNo());
            }
        });

        Set<Long> menuIds = new LinkedHashSet<Long>();
        for (MenuPrivilegeModel el : menuPrivileges) {
            menuIds.add(el.getMenuId());
        }

        Map<Long, List<Long>> menuMap = new HashMap<Long, List<Long>>();

        List<Long> menuList;

        // 得到MENUID集合
        Set<Long> menuIdList = new HashSet<Long>();
        for (MenuPrivilegeModel el : menuPrivileges) {
            menuIdList.add(el.getMenuId());
        }

        Long parentMenuId = 0L;
        MenuModel menuModel;
        for (Long el : menuIds) {
            menuModel = allMenuMap.get(el);
            if (menuModel == null) {
                logger.warn("通过ID" + el + "没有找到菜单信息");
                continue;
            }

            // 如果父ID不在列表中，那应该是第一级菜单，KEY 设为0。
            parentMenuId = menuIdList.contains(menuModel.getMenu().getParentMenuId()) ?
                    menuModel.getMenu().getParentMenuId() : 0;
            menuList = menuMap.get(parentMenuId);
            if (menuList == null) {
                menuList = new ArrayList<Long>();
                menuMap.put(parentMenuId, menuList);
            }
            menuList.add(el);
        }

        return menuMap;
    }
}
