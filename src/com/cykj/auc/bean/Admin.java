package com.cykj.auc.bean;

import java.math.BigDecimal;

public class Admin {

    private int rn;
    private BigDecimal adminId;
    private String account;
    private String pwd;
    private String roleId;
    private String adminName;
    private String departmentID;
    private String adminState;
    private String updateTime;

    public Admin() {

    }

    public Admin(int rn,BigDecimal adminId, String account, String pwd, String roleId, String adminName, String departmentID,String adminState, String updateTime) {
        this.rn = rn;
        this.adminId = adminId;
        this.account = account;
        this.pwd = pwd;
        this.roleId = roleId;
        this.adminName = adminName;
        this.departmentID = departmentID;
        this.adminState = adminState;
        this.updateTime = updateTime;
    }

    public BigDecimal getAdminId() {
        return adminId;
    }

    public void setAdminId(BigDecimal adminId) {
        this.adminId = adminId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }
}
