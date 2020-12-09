package com.cykj.auc.service;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.bean.PageBean;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public interface UserService {
    public PageBean<Admin> findUserByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public boolean changeSTate(String adminState,String account) throws SQLException;

    public boolean replacement(String account) throws SQLException;

    public boolean modification(String account,String departmentID,String roleId) throws SQLException;

    public boolean insert(String account, String pwd, String departmentID, String roleId, String adminName, Date date) throws SQLException;

    public int maxCount();
}
