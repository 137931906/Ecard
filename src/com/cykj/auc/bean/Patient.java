package com.cykj.auc.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Patient {

    private BigDecimal paId;
    private String patName;
    private String patSex;
    private BigDecimal patAge;
    private String np; //病人籍贯
    private String patStatus;  //身份证号
    private String phoneNum;
    private String nowAdd;
    private String  cardId;
    private BigDecimal money;
    private Date upDateTime;

    public Patient(){

    }

    public Patient(BigDecimal paId, String patName, String patSex, BigDecimal patAge, String np, String patStatus, String phoneNum, String nowAdd, String cardId, BigDecimal money, Date upDateTime) {
        this.paId = paId;
        this.patName = patName;
        this.patSex = patSex;
        this.patAge = patAge;
        this.np = np;
        this.patStatus = patStatus;
        this.phoneNum = phoneNum;
        this.nowAdd = nowAdd;
        this.cardId = cardId;
        this.money = money;
        this.upDateTime = upDateTime;
    }

    public BigDecimal getPaId() {
        return paId;
    }

    public void setPaId(BigDecimal paId) {
        this.paId = paId;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatSex() {
        return patSex;
    }

    public void setPatSex(String patSex) {
        this.patSex = patSex;
    }

    public BigDecimal getPatAge() {
        return patAge;
    }

    public void setPatAge(BigDecimal patAge) {
        this.patAge = patAge;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getPatStatus() {
        return patStatus;
    }

    public void setPatStatus(String patStatus) {
        this.patStatus = patStatus;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNowAdd() {
        return nowAdd;
    }

    public void setNowAdd(String nowAdd) {
        this.nowAdd = nowAdd;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(Date upDateTime) {
        this.upDateTime = upDateTime;
    }
}
