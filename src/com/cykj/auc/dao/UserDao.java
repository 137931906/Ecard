package com.cykj.auc.dao;

import com.cykj.auc.bean.Admin;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface UserDao {

    public List<Admin> findUserByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public BigDecimal findCount(HashMap<String,Object> condition);

    public boolean changeSTate(String adminState,String account) throws SQLException;

    public boolean replacement(String account) throws SQLException;

    public boolean modification(String account,String departmentID,String roleId) throws SQLException;

    public boolean insert(String account, String pwd, String departmentID, String roleId, String adminName, Date date) throws SQLException;

    public int maxCount();

}
