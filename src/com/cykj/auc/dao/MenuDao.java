package com.cykj.auc.dao;

import com.cykj.auc.bean.Menu;

import java.util.List;

public interface MenuDao {
    public List<Menu> findMenuByPid(int pId);
}
