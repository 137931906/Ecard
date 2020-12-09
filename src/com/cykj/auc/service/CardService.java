package com.cykj.auc.service;

import com.cykj.auc.bean.Card;
import com.cykj.auc.bean.PageBean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

public interface CardService {

    public PageBean<Card> findCardByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public boolean insert(String cardNum,String cardPr,int cardSuf);

    public BigDecimal maxCount();

    public boolean delete(int cardId);

    public boolean logOut(int cardId) throws SQLException;

    public BigDecimal count(HashMap<String,Object> cond);

    public boolean application(int getCardId,String cardPr,int gcNum,String gcId,int adminId);
}
