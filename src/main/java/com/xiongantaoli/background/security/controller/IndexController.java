/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiongantaoli.background.security.controller;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiongantaoli.background.entity.SysResource;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.mapper.SysResourceMapper;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.util.Utility;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysResourceMapper resourceMapper;

	/**
	 * 登录用户信息
	 * 
	 * @return
	 * @Description:
	 */
	@ModelAttribute("user")
	public SysUser User() {
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		return loginUser;
	}

	/**
	 * 页面加载方法
	 * 
	 * @param model
	 * @return
	 * @Description:
	 */
	@GetMapping("/")
	public String welcome(Model model, HttpServletRequest req) {
		// set集合保存的是引用不同地址的对象这里用于去掉重复的资源权限     !!!!!!LinkedHashSet去除重复同时保证排序
		Set<SysResource> sysMenu = new LinkedHashSet<SysResource>();	
		String loginUserName = Utility.getCurrentUsername();
		// 加载左侧菜单列表
	    List<SysResource> menuList = resourceMapper.selectByUsername(loginUserName);
		sysMenu.addAll(menuList);
		model.addAttribute("loginUserName", loginUserName);
		model.addAttribute("sysMenu", sysMenu);
		// 菜单加载完成
		return "index";
	}
	
	@GetMapping("/welco")
	public String welco() {
		return "welcome";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/test")
	public String test() {
		logger.info("您没有如此的访问权限");
		return "404";
	}

}
