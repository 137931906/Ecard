package com.cykj.auc.service.impl;

import com.cykj.auc.bean.Patient;
import com.cykj.auc.dao.SellCardDao;
import com.cykj.auc.service.SellCardService;
import com.cykj.auc.util.ObjectFactory;

import java.util.List;

public class SellCardServiceImpl implements SellCardService {

    SellCardDao sellCardDao = (SellCardDao) ObjectFactory.newInstance("com.cykj.auc.dao.impl.SellCardDaoImpl");
    @Override
    public boolean count(String cardNumber) {
        return sellCardDao.count(cardNumber);
    }

    @Override
    public boolean getCardService(String name, String age, int sex, String birthplace, String idNumber, String telephone, String address, int saveMoney, String cardNumber) {
        return sellCardDao.getCardService(name,age,sex,birthplace,idNumber,telephone,address,saveMoney,cardNumber);
    }

    @Override
    public List<Patient> cancel(String read) {
        return sellCardDao.cancel(read);
    }

    @Override
    public boolean quitCard(String read) {
        return sellCardDao.quitCard(read);
    }

    @Override
    public boolean changeCard(String read, String cardNum) {
        return sellCardDao.changeCard(read,cardNum);
    }
}
