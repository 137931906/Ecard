package com.cykj.auc.service.impl;

import com.cykj.auc.bean.GetCard;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.dao.GetCardDao;
import com.cykj.auc.service.GetCardService;
import com.cykj.auc.util.ObjectFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class GetCardServiceImpl implements GetCardService {

    GetCardDao getCardDao = (GetCardDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.GetCardDaoImpl");

    @Override
    public PageBean<GetCard> findCardByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<GetCard> getcard= getCardDao.findCardByPage(condition,curPage,pageSize);
        int totalRecords = getCardDao.findCounts(condition).intValue();
        PageBean<GetCard> pageBean = new PageBean<>(curPage,totalRecords,pageSize);
        pageBean.setList(getcard);
       return pageBean;
    }

    @Override
    public boolean apply(int gcNum, String gcId) {
        return getCardDao.apply(gcNum,gcId);
    }

    @Override
    public BigDecimal maxCount() {
        return getCardDao.maxCount();
    }

    @Override
    public BigDecimal count(HashMap<String, Object> cond) {
        return getCardDao.count(cond);
    }

    @Override
    public boolean application(int Id, String cardPr, int gcNum, String gcId, String adminName) {
        return getCardDao.application(Id,cardPr,gcNum,gcId,adminName);
    }

    @Override
    public boolean cardList(String pre, int num, int checkid,int id) {
        return getCardDao.cardList(pre,num,checkid,id);
    }
}
