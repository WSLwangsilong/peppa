package com.cjt.admin.controller.system.security;

import com.cjt.admin.controller.BaseController;
import com.cjt.entity.dto.ResultDTO;
import com.cjt.entity.model.system.security.MenuDO;
import com.cjt.service.system.security.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author caojiantao
 */
@RestController
@RequestMapping("/system/security/menu")
public class MenuController extends BaseController {

    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/getMenuById")
    public ResultDTO getMenuById(int id) {
        return success(menuService.getMenuById(id));
    }

    @GetMapping("/getMenus")
    public ResultDTO getMenus() {
        return success(menuService.listMenus());
    }

    @PostMapping("/saveMenu")
    public ResultDTO saveMenu(MenuDO menu) {
        // 字段校验
        if (StringUtils.isBlank(menu.getName())) {
            return failure("菜单名称不能为空");
        } else if (menu.getName().length() > 20) {
            return failure("菜单名称不能超过20个字符");
        } else if (menu.getOrder() == null) {
            return failure("菜单排序不能为空");
        }
        return menuService.saveMenu(menu) ? success("操作成功", menu) : failure("操作失败请重试");
    }

    @PostMapping("/deleteMenuById")
    public ResultDTO deleteMenuById(int id) {
        return menuService.deleteMenuById(id) ? success("操作成功") : failure("操作失败请重试");
    }

    @GetMapping("/getMenusByUserId")
    public ResultDTO getMenusByUserId(int userId) {
        return success(menuService.getMenusByUserId(userId));
    }
}
