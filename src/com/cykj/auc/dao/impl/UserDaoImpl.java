package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.dao.UserDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<Admin> findUserByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
            List<Admin> admins = new ArrayList<>();
            Connection con = null;
            try {
                con = DbHelper.getDbHelper().getConnection();//获取连接对象

                String sql = "select * from(select t.*,rowNum rn from (select * from admin where 1=1";

                //先判断是否查询有带参数
                if (condition.size()>0){
                    //如果有判断参数的类型
                    if (condition.containsKey("adminName") && !condition.get("adminName").equals("")){
                        //提示：当上述的sql语句结尾没有留出空隔，那么拼接的语句一开始就要留出空隔
                        sql+=" and adminName like '%"+condition.get("adminName")+"%'";
                    }
                    if (condition.containsKey("adminState") &&  !condition.get("adminState").equals("")){
                        sql+=" and adminState = '" +condition.get("adminState")+"'";
                    }
                    if (condition.containsKey("departmentId") &&  !condition.get("departmentId").equals("")){
                        sql+=" and departmentID = '" +condition.get("departmentId")+"'";
                    }
                    if (condition.containsKey("roleId") &&  !condition.get("roleId").equals("")){
                        sql+=" and roleId = '" +condition.get("roleId")+"'";
                    }
                }
                sql +=" order by adminName) t ) where rn between ? and ?";
                System.out.println(sql);
                QueryRunner queryRunner = new QueryRunner();
                Object[] params = {(curPage-1)*pageSize+1,curPage*pageSize};
                admins = queryRunner.query(con,sql,new BeanListHandler<>(Admin.class),params);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DbHelper.getDbHelper().close(con);
            }
            return admins;
    }

    @Override
    public BigDecimal findCount(HashMap<String, Object> condition) {
        BigDecimal records = new BigDecimal(0);
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*) from admin where 1=1";
            if (condition.size()>0){
                if (condition.containsKey("adminName") ){
                    sql+=" and adminName like '%"+condition.get("adminName")+"%'";
                }
                if (condition.containsKey("adminState") ){
                    sql+=" and adminState =  '"+condition.get("adminState")+"'";
                }
                if (condition.containsKey("departmentId") ){
                    sql+=" and departmentID = '" +condition.get("departmentId")+"'";
                }
                if (condition.containsKey("roleId") ){
                    sql+=" and roleId = '" +condition.get("roleId")+"'";
                }
            }
            PreparedStatement pst = null;
            pst = con.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            if (res.next()){
                records = res.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return records;
    }

    @Override
    public boolean changeSTate(String adminState, String account) throws SQLException {
        Connection con = null;
        con = DbHelper.getDbHelper().getConnection();
        String sql = "update Admin set adminState=? where account=?";
        System.out.println(sql);
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {adminState,account};
        int change=queryRunner.update(con,sql,params);
        DbHelper.getDbHelper().close(con);
        if (change>0){
            return true;
        }else {
            return false;
        }
    }



    @Override
    public boolean replacement(String account) throws SQLException {
        Connection con = null;
        con = DbHelper.getDbHelper().getConnection();
        String sql = "update Admin set pwd=123456 where account=?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {account};
        int change=queryRunner.update(con,sql,params);
        DbHelper.getDbHelper().close(con);
        if (change>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean modification(String account, String departmentID, String roleId) {
        Connection con = null;
        boolean flag = false;
        con = DbHelper.getDbHelper().getConnection();
        try {
            String sql = "update Admin set departmentID=? , roleId=? where account=?";
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {departmentID,roleId,account};
            System.out.println(sql);
            int change = queryRunner.update(con, sql, params);
            if (change > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {

            DbHelper.getDbHelper().close(con);
        }
        return flag;
    }

    @Override
    public boolean insert(String account, String pwd, String departmentID, String roleId, String adminName, Date date) throws SQLException {
        Connection con = null;
        boolean flag = false;
        con = DbHelper.getDbHelper().getConnection();
        try {
            String sql = "insert into admin(adminId,account,pwd,roleId,adminName,departmentID,adminState,updateTime)values(SEQUENCE1.nextVal,?,?,?,?,?,'启用',to_date(to_char(sysdate，'yyyy-mm-dd hh24:mi:ss'）,'yyyy-mm-dd hh24:mi:ss'))";
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {account,pwd,roleId,adminName,departmentID};
            System.out.println(sql);
            int change = queryRunner.update(con, sql, params);
            if (change > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {

            DbHelper.getDbHelper().close(con);
        }
        return flag;
    }

    @Override
    public int  maxCount() {
        int count =0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(adminID) from admin";
            pst = con.prepareStatement(sql);
            ResultSet res = pst.executeQuery();

            while (res.next()){
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return count;
    }
}



