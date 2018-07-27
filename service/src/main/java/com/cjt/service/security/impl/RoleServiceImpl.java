package com.cjt.service.security.impl;

import com.alibaba.fastjson.JSONObject;
import com.caojiantao.common.util.JsonUtils;
import com.cjt.dao.security.IMenuDAO;
import com.cjt.dao.security.IRoleDAO;
import com.cjt.dao.security.IRoleMenusDAO;
import com.cjt.entity.dto.RoleDTO;
import com.cjt.entity.model.security.Menu;
import com.cjt.entity.model.security.Role;
import com.cjt.service.security.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author caojiantao
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleDAO roleDAO;

    private final IMenuDAO menuDAO;

    private final IRoleMenusDAO roleMenusDAO;

    @Autowired
    public RoleServiceImpl(IRoleDAO roleDAO, IRoleMenusDAO roleMenusDAO, IMenuDAO menuDAO) {
        this.roleDAO = roleDAO;
        this.roleMenusDAO = roleMenusDAO;
        this.menuDAO = menuDAO;
    }

    @Override
    public List<Role> listRoleByUserId(long userId) {
        return roleDAO.listRoleByUserId(userId);
    }

    @Override
    public JSONObject listRoleByPage(RoleDTO roleDTO) {
        List<Role> roles = roleDAO.getList(roleDTO);
        int total = roleDAO.getListCount(roleDTO);
        return JsonUtils.toPageData(roles, total);
    }

    @Override
    public Role getRoleById(int id) {
        Role role = roleDAO.getById(id);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRole(Role role, List<Integer> menuIds) {
        if (role.getId() == null) {
            roleDAO.insert(role);
            roleMenusDAO.saveRoleMenus(role.getId(), menuIds);
            return role.getId() > 0;
        } else {
            roleMenusDAO.removeRoleMenus(role.getId());
            roleMenusDAO.saveRoleMenus(role.getId(), menuIds);
            return roleDAO.updateById(role) > 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeRole(int id) {
        roleMenusDAO.removeRoleMenus(id);
        return roleDAO.deleteById(id) > 0;
    }
}
