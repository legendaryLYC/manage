package com.xiongantaoli.background.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiongantaoli.background.entity.SysRole;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.entity.SysUserRoles;
import com.xiongantaoli.background.mapper.SysRoleMapper;
import com.xiongantaoli.background.mapper.SysUserMapper;
import com.xiongantaoli.background.mapper.SysUserRolesMapper;
import com.xiongantaoli.background.security.service.RoleService;

@Service
@Transactional(readOnly=true, rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRolesMapper userRolesMapper;
	@Autowired
	private SysRoleMapper roleMapper; //角色数据访问类
	
	@Override
	public List<SysRole> selectByUsername(String username) {
		List<SysRole> sysRoles = new ArrayList<>();
		SysUser user = userMapper.selectByUsername(username);
		if(user != null) {
			List<SysUserRoles> userRoles = userRolesMapper.selectByUserId(user.getId()); // 获取用户关联的角色信息
			for(SysUserRoles userRole : userRoles) {
				SysRole role = roleMapper.selectByPrimaryKey(userRole.getRolesId());
				sysRoles.add(role);
			}
		}
		return sysRoles;
	}

}
