package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Card;
import com.cykj.auc.bean.GetCard;
import com.cykj.auc.dao.GetCardDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetCardDaoImpl implements GetCardDao {
    @Override
    public List<GetCard> findCardByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<GetCard> getCards = new ArrayList<>();
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();//获取连接对象
            String sql = "select * from(select t.*,rowNum rn from (select * from GetCard where 1=1";
            //先判断是否查询有带参数
            if (condition.size() > 0) {
                //如果有判断参数的类型
                if (condition.containsKey("minInputTime") && condition.containsKey("maxInputTime")) {
                    sql += "and to_char(inputTime,'yyyy-MM-dd') >='" + condition.get("minInputTime") + "'and to_char(inputTime,'yyyy-MM-dd') <='" + condition.get("maxInputTime") + "'";
                }
                if (condition.containsKey("selCardState") && !condition.get("selCardState").equals("")) {
                    sql += " and gcState = '" + condition.get("selCardState") + "'";
                }
                if (condition.containsKey("gcId")) {
                    sql += " and gcId like '%" + condition.get("gcId") + "%'";
                }
            }
            sql += " order by gcId ) t ) where rn between ? and ?";
            System.out.println(sql);
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {(curPage - 1) * pageSize + 1, curPage * pageSize};
            getCards = queryRunner.query(con, sql, new BeanListHandler<>(GetCard.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        return getCards;
    }

    @Override
    public BigDecimal findCounts(HashMap<String, Object> condition) {
        BigDecimal records = new BigDecimal(0);
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*) from getCard where 1=1";
            if (condition.size() > 0) {
                //如果有判断参数的类型
                if (condition.containsKey("minInputTime") && condition.containsKey("maxInputTime")) {
                    sql += "and to_char(inputTime,'yyyy-MM-dd') >='" + condition.get("minInputTime") + "'and to_char(inputTime,'yyyy-MM-dd') <='" + condition.get("maxInputTime") + "'";
                }
                if (condition.containsKey("selCardState") && !condition.get("selCardState").equals("")) {
                    sql += " and gcState = '" + condition.get("selCardState") + "'";
                }
                if (condition.containsKey("gcId")) {
                    sql += " and gcId like '%" + condition.get("gcId") + "%'";
                }
            }
//            QueryRunner queryRunner = new QueryRunner();
//            records = (BigDecimal) queryRunner.query(con,sql,new ScalarHandler());
            PreparedStatement pst = null;
            pst = con.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                records = res.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        return records;
    }

    @Override
    public boolean apply(int gcNum, String gcId) {
        Connection con = null;
        int num = 0;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "insert into GetCard(Id,gcNum,gcId,gcTime,state,gcState)values(SEQUENCE3.nextVal,?,?,to_date(to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),1,'待销售')";
            QueryRunner queryRunner = new QueryRunner();
            Object[] params = {gcNum, gcId};
            num = queryRunner.update(con, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BigDecimal maxCount() {
        BigDecimal count = new BigDecimal(0);
        Connection con = null;

        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*)from GetCard";
            QueryRunner queryRunner = new QueryRunner();
            count = (BigDecimal) queryRunner.query(con, sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }
        return count;
    }

    @Override
    public BigDecimal count(HashMap<String, Object> cond) {
        BigDecimal count = new BigDecimal(0);
        Connection con = null;
        try {
            con = DbHelper.getDbHelper().getConnection();
            String sql = "select count(*) from card where 1=1";
            if (cond.size() > 0) {
                if (cond.containsKey("cardPr")) {
                    sql += "and cardPr = '" + cond.get("cardPr") + "'";
                }
            }
            sql += "and cardState = 1";
            PreparedStatement pst = null;
            pst = con.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                count = res.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.getDbHelper().close(con);
        }

        return count;
    }

    @Override
    public boolean application(int Id, String cardPr, int gcNum, String gcId, String adminName) {
        return false;
    }

    @Override
    public boolean cardList(String pre,int num,int checkid,int id){
        List<Card> cardList=new ArrayList<>();
        Connection  con = DbHelper.getDbHelper().getConnection();
        String sql="select * from (select rownum rn," +
                "t.* from (select cardnum from card where cardpr =? and cardstate=? order by CARDSUF asc)t)" +
                "where rn between ? and ?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params1 = {pre,1,1,num};
        try {
            con.setAutoCommit(false);
            cardList= queryRunner.query(con,sql,new BeanListHandler<>(Card.class),params1);
            if(cardList.size()>0){
                String sql2="update card set cardstate=2 where cardnum=?";
                Object[][]params2 =new Object[num][1];
                for (int i = 0; i <cardList.size(); i++) {
                    params2[i][0]=cardList.get(i).getCardNum();
                    System.out.println(params2);
                }
                int flag=queryRunner.batch(con,sql2,params2).length;
                if(flag==cardList.size()){
                    String sql3="update getcard set state=?,userid=?,apprtime=sysdate where id=?";
                    Object[] params3 = {2,checkid,id};
                    int  flag3 = queryRunner.update(con, sql3, params3);
                    if(flag3>0){
                        con.commit();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        DbHelper.getDbHelper().close(con);
      return  false;
    }

//    @Override
//    public boolean application(int num,String gcid, int checkid,String cardpr) {
//        Connection  con = DbHelper.getDbHelper().getConnection();
//
////
////            String sql1 = "update card set cardState = 2,cardSeller = ? where cardId in (select a.cardId from(select t.cardId,rowNum rn from(select cardId from card where cardPr = ? and cardState = 1 order by cardId)t)a where rn between 1 and ?)";
//            QueryRunner queryRunner = new QueryRunner();
//            Object[] params1 = {id, cardPr, gcNum};
//            try{
////                con.setAutoCommit(false);
////               int  a = queryRunner.update(con, sql1, params1);
////            if (a > 0) {
////                String sql2 = "update getCard set state = 2 , userId = ? , apprTime = to_date(to_char(sysdate，'yyyy-mm-dd hh24:mi:ss'）,'yyyy-mm-dd hh24:mi:ss') where Id = ?";
////                Object[] params2 = {adminName, Id};
////               int  b = queryRunner.update(con, sql2, params2);
////                if (b> 0) {
////                    con.commit();
////                    return true;
////                }
////            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//                try {
//                    con.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        DbHelper.getDbHelper().close(con);
//            return false;
//    }
}