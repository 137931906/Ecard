package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.dao.AdminDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.Connection;
import java.sql.SQLException;


public class AdminDaoImpl implements AdminDao {


    //用户登入
    @Override
    public Admin login(String account, String pwd) {
        Admin admin = null;
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();//创建连接对象
            String sql = "select * from admin where account = ? and pwd = ?";//声明sql语句
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {account,pwd};
            admin = queryRunner.query(con,sql,new BeanHandler<>(Admin.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }

        return admin;
    }



}
