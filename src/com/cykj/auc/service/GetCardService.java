package com.cykj.auc.service;

import com.cykj.auc.bean.GetCard;
import com.cykj.auc.bean.PageBean;

import java.math.BigDecimal;
import java.util.HashMap;

public interface GetCardService {

    public PageBean<GetCard> findCardByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public boolean apply(int gcNum,String gcId);

    public BigDecimal maxCount();

    public BigDecimal count(HashMap<String,Object> cond);

    public boolean application(int Id,String cardPr,int gcNum,String gcId,String adminName);
    public boolean cardList(String pre,int num,int checkid,int id);
}

