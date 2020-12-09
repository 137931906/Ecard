package com.cykj.auc.bean;

import java.math.BigDecimal;


public class Card {
    private int rn;
    private BigDecimal cardId;
    private String cardPr;//卡前缀
    private BigDecimal careSuf;//卡后缀
    private String cardNum;
    private String inputTime;
    private String cardState;
    private String updateTime;
    private String recipient;
    private String patient;
    private String cardSeller;

    public Card() {

    }

    public Card(int rn, BigDecimal cardId, String cardPr, BigDecimal careSuf, String cardNum, String inputTime, String cardState, String updateTime, String recipient, String patient, String cardSeller) {
        this.rn = rn;
        this.cardId = cardId;
        this.cardPr = cardPr;
        this.careSuf = careSuf;
        this.cardNum = cardNum;
        this.inputTime = inputTime;
        this.cardState = cardState;
        this.updateTime = updateTime;
        this.recipient = recipient;
        this.patient = patient;
        this.cardSeller = cardSeller;
    }

    public BigDecimal getCardId() {
        return cardId;
    }

    public void setCardId(BigDecimal cardId) {
        this.cardId = cardId;
    }

    public String getCardPr() {
        return cardPr;
    }

    public void setCardPr(String cardPr) {
        this.cardPr = cardPr;
    }

    public BigDecimal getCareSuf() {
        return careSuf;
    }

    public void setCareSuf(BigDecimal careSuf) {
        this.careSuf = careSuf;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getCardState() {
        return cardState;
    }

    public void setCardState(String cardState) {
        this.cardState = cardState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getCardSeller() {
        return cardSeller;
    }

    public void setCardSeller(String cardSeller) {
        this.cardSeller = cardSeller;
    }
}
