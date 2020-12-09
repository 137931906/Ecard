package com.cykj.auc.service;

import com.cykj.auc.bean.Menu;

import java.util.HashMap;
import java.util.List;

public interface MenuService {
    public HashMap<String, List<Menu>> findMenuMap(int pId);
}
