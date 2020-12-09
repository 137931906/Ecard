package com.cykj.auc.bean;

import java.math.BigDecimal;

public class SellCard {

    private BigDecimal scId;
    private String cardPr;
    private String cardSuf;
    private String cardNum;
    private String updateTime;
    private String state;

    public SellCard() {

    }

    public SellCard(BigDecimal scId, String cardPr, String cardSuf, String cardNum, String updateTime, String state) {
        this.scId = scId;
        this.cardPr = cardPr;
        this.cardSuf = cardSuf;
        this.cardNum = cardNum;
        this.updateTime = updateTime;
        this.state = state;
    }

    public BigDecimal getScId() {
        return scId;
    }

    public void setScId(BigDecimal scId) {
        this.scId = scId;
    }

    public String getCardPr() {
        return cardPr;
    }

    public void setCardPr(String cardPr) {
        this.cardPr = cardPr;
    }

    public String getCardSuf() {
        return cardSuf;
    }

    public void setCardSuf(String cardSuf) {
        this.cardSuf = cardSuf;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
