package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Card;
import com.cykj.auc.dao.CardDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public List<Card> findCardByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<Card> cards = new ArrayList<>();
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();//获取连接对象
            String sql = "select * from(select t.*,rowNum rn from (select * from Card where 1=1";
            //先判断是否查询有带参数
            if (condition.size() > 0) {
                //如果有判断参数的类型
                if (condition.containsKey("minCardNum") && condition.containsKey("maxCardNum")) {
                    //提示：当上述的sql语句结尾没有留出空隔，那么拼接的语句一开始就要留出空隔
                    sql += " and cardSuf >= '"+condition.get("minCardNum") + "' and cardSuf <= '" + condition.get("maxCardNum") + "'";
                }
                if (condition.containsKey("minInputTime") && condition.containsKey("maxInputTime")) {
                    sql+=" and to_char(inputTime,'yyyy-MM-dd') >='" + condition.get("minInputTime")+"'and to_char(inputTime,'yyyy-MM-dd') <='" + condition.get("maxInputTime")+"'";
                }
                if (condition.containsKey("CardState") && !condition.get("CardState").equals("")) {
                    sql += " and CardState = '" + condition.get("CardState")+ "'";
                }
                if (condition.containsKey("cardNum") && !condition.get("cardNum").equals("")){
                    sql+=" and cardSuf = '"+condition.get("CardNum") + "'";
                }
            }
            sql += " order by cardNum asc) t ) where rn between ? and ?";
            System.out.println(sql);
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {(curPage - 1) * pageSize + 1, curPage * pageSize};
            cards = queryRunner.query(con, sql, new BeanListHandler<>(Card.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        return cards;
    }

    @Override
    public BigDecimal findCounts(HashMap<String,Object> condition) {
        BigDecimal records = new BigDecimal(0);
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*) from card where 1=1";
            if (condition.size() > 0) {
                //如果有判断参数的类型
                if (condition.containsKey("minCardNum") && condition.containsKey("maxCardNum")) {
                    //提示：当上述的sql语句结尾没有留出空隔，那么拼接的语句一开始就要留出空隔
                    sql += " and cardSuf >= '"+condition.get("minCardNum") + "' and cardSuf <= '" + condition.get("maxCardNum") + "'";
                }
                if (condition.containsKey("minInputTime") && condition.containsKey("maxInputTime")) {
                    sql+=" and to_char(inputTime,'yyyy-MM-dd') >='" + condition.get("minInputTime")+"'and to_char(inputTime,'yyyy-MM-dd') <='" + condition.get("maxInputTime")+"'";
                }
                if (condition.containsKey("CardState") && !condition.get("CardState").equals("")) {
                    sql += " and CardState = '" + condition.get("CardState") + "'";
                }
                if (condition.containsKey("cardNum") && !condition.get("cardNum").equals("")){
                    sql+=" and cardNum like '%"+condition.get("cardNum")+"%'";
                }
            }
            QueryRunner queryRunner = new QueryRunner();
            records = (BigDecimal) queryRunner.query(con,sql,new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return records;

    }

    //卡入库
    @Override
    public boolean insert(String cardNum,String cardPr,int cardSuf) {
        Connection con = null;
        int num = 0;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "insert into card(cardId,cardPr,cardNum,cardSuf,inputTime,cardState,upDateTime)values(SEQUENCE2.nextVal,?,?,?,to_date(to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),1,to_date(to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'))";
            QueryRunner queryRunner = new QueryRunner();
            Object [] params = {cardPr,cardNum,cardSuf};
            num = queryRunner.update(con,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        if (num>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public BigDecimal maxCount() {
        BigDecimal count = new BigDecimal(0);
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*)from card";
            QueryRunner queryRunner = new QueryRunner();
            count = (BigDecimal) queryRunner.query(con,sql,new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbHelper.getDbHelper().close(con);
        }
        return count;
    }

    @Override
    public boolean delete(int cardId) {
        Connection con = null;
        con = DbHelper.getDbHelper().getConnection();
        String sql = "update Card set cardState='已删除' where cardId=?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {cardId};
        int change= 0;
        try {
            change = queryRunner.update(con,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.getDbHelper().close(con);
        if (change>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean logOut(int cardId){
        Connection con = null;
        con = DbHelper.getDbHelper().getConnection();
        String sql = "update Card set cardState='已注销' where cardId=?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {cardId};
        int change= 0;
        try {
            change = queryRunner.update(con,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbHelper.getDbHelper().close(con);
        if (change>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public BigDecimal count(HashMap<String, Object> cond) {
        BigDecimal count = new BigDecimal(0);
        Connection con = null;
        con = DbHelper.getDbHelper().getConnection();
        String sql = "select count(*) from getCard where 1=1";
        if (cond.size() > 0){
            if (cond.containsKey("cardPr")){
                sql += "and cardPr = '" + cond.get("cardPr")+"'";
            }
        }
        return null;
    }

    @Override
    public boolean application(int getCardId, String cardPr, int gcNum, String gcId, int adminId) {
        return false;
    }



}