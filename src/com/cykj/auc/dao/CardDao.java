package com.cykj.auc.dao;

import com.cykj.auc.bean.Card;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface CardDao {

    public List<Card> findCardByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public BigDecimal findCounts(HashMap<String,Object> condition) ;

    public boolean insert(String cardNum,String cardPr,int cardSuf);

    public BigDecimal maxCount();

    public boolean delete(int cardId);

    public boolean logOut(int cardId);

    public BigDecimal count(HashMap<String,Object> cond);

    public boolean application(int getCardId,String cardPr,int gcNum,String gcId,int adminId);
}
