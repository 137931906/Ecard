package com.cykj.auc.service.impl;

import com.cykj.auc.bean.Card;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.dao.CardDao;
import com.cykj.auc.service.CardService;
import com.cykj.auc.util.ObjectFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CardServiceImpl implements CardService {

    CardDao cardDao = (CardDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.CardDaoImpl");
    @Override
    public PageBean<Card> findCardByPage(HashMap<String,Object> condition, int curPage, int pageSize)  {
        List<Card> cards= cardDao.findCardByPage(condition,curPage,pageSize);
        int totalRecords = cardDao.findCounts(condition).intValue();
        PageBean<Card> pageBean = new PageBean<>(curPage,totalRecords,pageSize);
        pageBean.setList(cards);
        return pageBean;
    }

    @Override
    public boolean insert(String cardNum, String cardPr, int cardSuf) {
        System.out.println(cardPr+"=="+cardNum+"=="+cardSuf);
        return cardDao.insert(cardNum,cardPr,cardSuf);
    }

    @Override
    public BigDecimal maxCount() {
        return cardDao.maxCount();
    }

    @Override
    public boolean delete(int cardId) {
        return cardDao.delete(cardId);
    }

    @Override
    public boolean logOut(int cardId) throws SQLException {
            return cardDao.logOut(cardId);
    }

    @Override
    public BigDecimal count(HashMap<String, Object> cond) {
        return null;
    }

    @Override
    public boolean application(int getCardId, String cardPr, int gcNum, String gcId, int adminId) {
        return false;
    }

}
