package com.cykj.auc.bean;

import java.math.BigDecimal;

public class GetCard {

    private int rn;
    private BigDecimal id;
    private BigDecimal gcNum;
    private String gcId;
    private String gcTime;
    private BigDecimal userId;
    private String apprTime;
    private BigDecimal state;
    private String gcState;

    public GetCard() {

    }

    public GetCard(int rn,BigDecimal id, BigDecimal gcNum, String gcId, String gcTime, BigDecimal userId, String apprTime, BigDecimal state,String gcState) {
        this.rn = rn;
        this.id = id;
        this.gcNum = gcNum;
        this.gcId = gcId;
        this.gcTime = gcTime;
        this.userId = userId;
        this.apprTime = apprTime;
        this.state = state;
        this.gcState = gcState;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getGcNum() {
        return gcNum;
    }

    public void setGcNum(BigDecimal gcNum) {
        this.gcNum = gcNum;
    }

    public String getGcId() {
        return gcId;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public String getGcTime() {
        return gcTime;
    }

    public void setGcTime(String gcTime) {
        this.gcTime = gcTime;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getApprTime() {
        return apprTime;
    }

    public void setApprTime(String apprTime) {
        this.apprTime = apprTime;
    }

    public BigDecimal getState() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }

    public String getGcState() {
        return gcState;
    }

    public void setGcState(String gcState) {
        this.gcState = gcState;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }
}
