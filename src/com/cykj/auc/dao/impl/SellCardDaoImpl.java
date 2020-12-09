package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Patient;
import com.cykj.auc.dao.SellCardDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellCardDaoImpl implements SellCardDao {
    @Override
    public boolean count(String cardNumber) {
        Connection con = null;
        int n = 0;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select * from card where CARDNUM=? and cardState=2";
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {cardNumber};
            n = queryRunner.update(con, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getCardService(String name, String age, int sex, String birthplace, String idNumber, String telephone, String address, int saveMoney, String cardNumber) {
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "insert into patient(paId,patName,patAge,patSex,np,patStatus,phoneNum,nowAdd,money,upDateTime,cardId)values(SEQUENCE4.nextVal,?,?,?,?,?,?,?,?,sysdate,?)";
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {name, age, sex, birthplace, idNumber, telephone, address, saveMoney, cardNumber};
            int a = queryRunner.update(con, sql, params);
            if (a > 0) {
                String sql2 = "update card set cardState = 3 where cardNum = ?";
                Object[] params2 = {cardNumber};
                int b = queryRunner.update(con, sql2, params2);
                if (b > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
            return false;
        }
    }


    @Override
    public List<Patient> cancel(String read) {
        List<Patient> patients = new ArrayList<>();
        Connection con = null;
        int n = 0;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select * from patient where cardId = ? ";
            QueryRunner queryRunner = new QueryRunner();
            Object [] params = {read};
            patients = queryRunner.query(con,sql,new BeanListHandler<>(Patient.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return patients;
    }

    @Override
    public boolean quitCard(String read) {
        boolean flag = false;
        Connection con=null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql1 = "delete from patient where cardId = ?";
            QueryRunner queryRunner = new QueryRunner();
            Object [] params = {read};
            int a = queryRunner.update(con,sql1,params);
            if (a>0){
                String sql2 = "update CARD set cardState = 2 where cardNum = ?";
                Object [] params2 = {read};
                int b = queryRunner.update(con,sql2,params2);
                if (b>0) {
                    return  true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return false;
    }

    @Override
    public boolean changeCard(String read, String cardNum) {
        boolean flag = false;
        Connection con=null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql1 = "update PATIENT set cardID = ? where cardID = ?";
            QueryRunner queryRunner = new QueryRunner();
            Object [] params = {cardNum,read};
            int a = queryRunner.update(con,sql1,params);
            if (a>0) {
                String sql2 = "update CARD set cardState = 2 where cardNum = ? and cardNum = ?";
                Object [] params2 = {read,cardNum};
                int b = queryRunner.update(con,sql2,params2);
                if (b>0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return false;
    }
}
