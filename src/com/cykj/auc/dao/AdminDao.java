package com.cykj.auc.dao;

import com.cykj.auc.bean.Admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface AdminDao {

    public Admin login(String account, String pwd);

}
