package com.cykj.auc.service.impl;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.dao.UserDao;
import com.cykj.auc.service.UserService;
import com.cykj.auc.util.ObjectFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = (UserDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.UserDaoImpl");
    @Override
    public PageBean<Admin> findUserByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<Admin> admins= userDao.findUserByPage(condition,curPage,pageSize);
        int totalRecords = userDao.findCount(condition).intValue();
        PageBean<Admin> pageBean = new PageBean<>(curPage,totalRecords,pageSize);
        pageBean.setList(admins);
        return pageBean;
    }

    @Override
    public boolean changeSTate(String adminState, String account) throws SQLException {
        return userDao.changeSTate(adminState,account);
    }

    @Override
    public boolean replacement(String account) throws SQLException {
        return userDao.replacement(account);
    }

    @Override
    public boolean modification(String account, String departmentID, String roleId) throws SQLException {
        return userDao.modification(account,departmentID,roleId);
    }

    @Override
    public boolean insert(String account, String pwd, String departmentID, String roleId, String adminName, Date date) throws SQLException {
        return userDao.insert(account,pwd,departmentID,roleId,adminName,date);
    }

    @Override
    public int maxCount() {
        return userDao.maxCount();
    }

}
