package com.xiongantaoli.background.mapper;

import java.util.List;

import com.xiongantaoli.background.entity.SysRole;
import com.xiongantaoli.background.entity.SysUser;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);
    
    /**
     	* 根据查询条件检索角色信息
     * @param role
     * @return
     */
    List<SysRole> selectByRole(SysRole role);
    
    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
               * 通过用户id查询角色信息
     * @param id
     * @return
     */
    List<SysRole> selectRolesByUserID(Long id);
    
    /**
	 * 根据角色名查找唯一角色
	 * @param userName
	 * @return
	 */
    SysRole selectByRolename(String name);
}