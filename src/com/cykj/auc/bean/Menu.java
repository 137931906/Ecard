package com.cykj.auc.bean;

import java.math.BigDecimal;

public class Menu {

    private BigDecimal menuId;
    private BigDecimal nameId;
    private BigDecimal pId;
    private String url;
    private String MenuName;

    public Menu() {

    }

    public Menu(BigDecimal menuId,BigDecimal nameId,BigDecimal pId, String url,String MenuName) {
        this.menuId = menuId;
        this.nameId = nameId;
        this.pId = pId;
        this.url = url;
        this.MenuName = MenuName;
    }

    public BigDecimal getMenuId() {
        return menuId;
    }

    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public BigDecimal getpId() {
        return pId;
    }

    public void setpId(BigDecimal pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getNameId() {
        return nameId;
    }

    public void setNameId(BigDecimal nameId) {
        this.nameId = nameId;
    }
}
