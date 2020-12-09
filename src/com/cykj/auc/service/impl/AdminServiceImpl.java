package com.cykj.auc.service.impl;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.dao.AdminDao;
import com.cykj.auc.service.AdminService;
import com.cykj.auc.util.ObjectFactory;


public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = (AdminDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.AdminDaoImpl");

    @Override
    public Admin login(String account, String pwd) {

        return adminDao.login(account,pwd);
    }

}