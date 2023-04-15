package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.convertor.MenuConvertor;
import com.xavier.pms.dao.MenuMapper;
import com.xavier.pms.dto.MenuDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Menu;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IMenuService;
import com.xavier.pms.vo.MenuVo;
import com.xavier.pms.vo.RouterVo;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-菜单实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<RouterVo> getUserMenu(UserInfoVo userInfoVo) {
        return MenuConvertor.toRouterVoList(getUserMenuList(userInfoVo));
    }

    private List<Menu> getUserMenuList(UserInfoVo userInfoVo) {
        if (userInfoVo.getIsAdmin()) {
            return list(Menu.gw().eq(Menu::getIsEnable, 1).in(Menu::getMenuType, CollUtil.newArrayList(Constant.MENU_DIRECTORY, Constant.MENU_MENU)));
        }
        // todo 非管理员的菜单
        return new ArrayList<>();
    }

    @Override
    public List<MenuVo> getTree(UserInfoVo userInfoVo) {
        List<Menu> list = new ArrayList<>();
        if (userInfoVo.getIsAdmin()) {
            list = list(Menu.gw().eq(Menu::getIsEnable, 1));
        }
        // todo 非管理员的菜单
        return MenuConvertor.toTree(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteMenu(Long id) {
        getBaseMenu(id);
        return super.removeById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createMenu(MenuDto menuDto) {
        Menu menu = MenuConvertor.toMenu(menuDto);
        menu.setId(null);
        super.save(menu);
        return menu.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateMenu(MenuDto menuDto) {
        getBaseMenu(menuDto.getId());
        Menu menu = MenuConvertor.toMenu(menuDto);
        return super.updateById(menu);
    }

    @Override
    public MenuVo getMenu(Long id) {
        return MenuConvertor.toMenuVo(getBaseMenu(id));
    }


    @Override
    public Menu getBaseMenu(Long id) {
        Menu menu = super.getById(id);
        if (Objects.isNull(menu)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "菜单");
        }
        return menu;
    }


}
