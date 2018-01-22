package com.cjt.dao.admin.security;

import com.cjt.entity.admin.security.Role;

import java.util.List;

/**
 * @author caojiantao
 */
public interface IRoleDAO {

    /**
     * 根据username获取角色集合
     * @param userId 用户ID
     * @return 角色集合
     */
    List<Role> listRoleByUserId(long userId);
}
