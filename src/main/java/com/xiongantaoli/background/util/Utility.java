package com.xiongantaoli.background.util;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @Title: Utility
 * @Description:公用工具类
 * @Author:杨凯旋
 * @Since:2017年7月20日
 * @Version:1.1.0
 */
public class Utility {

	/**
	 * 密码加密
	 * 
	 * @param password
	 *            未加密文本
	 * @return 加密后的文本
	 */
	public static String QuickPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	/**
	 * 校验密码正确错误
	 * 
	 * @param rawPassword
	 *            未加密文本
	 * @param encodedPassword
	 *            加密文本
	 * @return true:密码正确 ，false密码错误
	 */
	public static Boolean checkPassword(String rawPassword, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	/**
	 * 
	 * security 查询登陆用户
	 * 
	 * @return 登录者用户名
	 */
	public static String getCurrentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof Principal) {
			return ((Principal) principal).getName();
		}
		return String.valueOf(principal);
	}

	/**
	 * 
	 * security 查询登陆用户
	 * 
	 * @return 登录者用户
	 */
	public static UserDetails getCurrentUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}
}
