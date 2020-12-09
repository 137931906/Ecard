package com.cykj.auc.service;

import com.cykj.auc.bean.Patient;

import java.util.List;

public interface SellCardService {

    public boolean count(String cardNumber) ;

    public boolean getCardService(String name,String age,int sex,String birthplace,String idNumber,String telephone,String address,int saveMoney,String cardNumber);

    public List<Patient> cancel(String read);

    //退卡
    public boolean quitCard(String read);

    //换卡
    public boolean changeCard(String read,String cardNum);
}
