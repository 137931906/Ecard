package com.cykj.auc.service.impl;

import com.cykj.auc.bean.Menu;
import com.cykj.auc.dao.MenuDao;
import com.cykj.auc.service.MenuService;
import com.cykj.auc.util.ObjectFactory;

import java.util.HashMap;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    MenuDao menuDao = (MenuDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.MenuDaoImpl");

    @Override
    public HashMap<String, List<Menu>> findMenuMap(int pId) {
        List<Menu> fatherMenus = menuDao.findMenuByPid(0);
        HashMap menuMap = new HashMap();
        for (Menu menu : fatherMenus) {
            List<Menu> sonMenus = menuDao.findMenuByPid(menu.getMenuId().intValue());//因为id的数据类型是BigDecimal,所以要用.intValue
            menuMap.put(menu.getMenuName(), sonMenus); //使用HashMap获取到菜单名和对应的子菜单
        }
        return menuMap;

    }
}
