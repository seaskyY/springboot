
package com.tc.pes.web.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 判断页面是否有spring security 权限
 */
public class AuthorityUtils {
	private static Properties configurer;
    /**
     * Gets the user name of the user from the Authentication object
     * 
     * @return the user name as string
     * @author liming
     * @version 1.0.0
     */
    public static String getPrincipal() {
    
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "Guest";
        }
    }
    
    /**
     * Is the user granted all of the grantedAuthorities passed in
     * 
     * @param checkForAuths
     *            the check for auths
     * @return true if user has all of the listed authorities/roles, otherwise false
     * @author liming
     * @version 1.0.0
     */
    public static boolean allGranted(String[] checkForAuths) {
    
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                continue;
            return false;
        }
        return true;
    }
    
    /**
     * Is the user granted any of the grantedAuthorities passed into
     * 
     * @param checkForAuths
     *            the check for auths
     * @return true if user has any of the listed authorities/roles, otherwise false
     * @author liming
     * @version 1.0.0
     */
    public static boolean anyGranted(String[] checkForAuths) {
    
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return true;
        }
        return false;
    }
    
    /**
     * Has any role.
     * 
     * @param checkForAuths
     *            the check for auths
     * @return the boolean
     * @author liming
     * @version 1.0.0
     */
    public static boolean hasAnyRole(List<String> checkForAuths) {
    
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return true;
        }
        return false;
    }
    
    /**
     * Has any role.
     * 
     * @author liufei
     * @version 1.0.0
     * @param checkForAuth
     *           
     * @return the boolean
     */
    public static boolean hasAnyRole(String checkForAuth)
    {
    
        return getUserAuthorities().contains(checkForAuth);
    }
    
    /**
     * is the user granted none of the supplied roles
     * 
     * @param checkForAuths
     *            the check for auths
     * @return true only if none of listed roles are granted
     * @author liming
     * @version 1.0.0
     */
    public static boolean noneGranted(String[] checkForAuths) {
    
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return false;
        }
        return true;
    }
    
    private static Set<String> getUserAuthorities() {
    
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = new HashSet<String>();
        if (obj instanceof UserDetails) {
            Collection<GrantedAuthority> gas = (Collection<GrantedAuthority>) ((UserDetails) obj).getAuthorities();
            for (GrantedAuthority ga : gas) {
                roles.add(ga.getAuthority());
            }
        }
        return roles;
    }
    public static String getSysConfig(String urlConfig){
   	 return configurer.getProperty(urlConfig);
   }

	public Properties getConfigurer() {
		return configurer;
	}

	public void setConfigurer(Properties configurer) {
		AuthorityUtils.configurer = configurer;
	}
}
