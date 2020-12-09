package com.cykj.auc.dao;

import com.cykj.auc.bean.GetCard;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface GetCardDao {

    public List<GetCard> findCardByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public BigDecimal findCounts(HashMap<String,Object> condition) ;

    public boolean apply(int gcNum,String gcId);

    public BigDecimal maxCount();

    public BigDecimal count(HashMap<String,Object> cond);

    public boolean application(int Id,String cardPr,int gcNum,String gcId,String adminName);

    public boolean cardList(String pre,int num,int checkid,int id );
}
