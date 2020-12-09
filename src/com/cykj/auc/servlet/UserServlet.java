package com.cykj.auc.servlet;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.service.UserService;
import com.cykj.auc.util.ObjectFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    UserService userService = (UserService) ObjectFactory.newInstance("com.cykj.auc.service.impl.UserServiceImpl");

    //用户表格，分页
    public String info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String adminName = request.getParameter("adminName");
        String adminState = request.getParameter("adminState");
        String departmentId = request.getParameter("departmentId");
        String roleId = request.getParameter("roleId");
        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (adminName != null && !adminName.trim().equals("")) {
            condition.put("adminName", adminName);
        }
        if (adminState != null && !adminState.trim().equals("")) {
            condition.put("adminState", adminState);
        }
        if (departmentId != null && !departmentId.trim().equals("")) {
            condition.put("departmentId", departmentId);
        }
        if (roleId != null && !roleId.trim().equals("")) {
            condition.put("roleId", roleId);
        }
        PageBean<Admin> pageBean = userService.findUserByPage(condition, curPage, 5);
        request.setAttribute("adminName", adminName);
        request.setAttribute("adminState", adminState);
        request.setAttribute("departmentId",departmentId);
        request.setAttribute("roleId",roleId);
        request.setAttribute("pageBean", pageBean);
        return "jsp/user_list.jsp";


    }
        //修改禁用启用状态
    public void changeState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String adminState = request.getParameter("adminState");
        String adminStates = request.getParameter("adminStates");
        String account = request.getParameter("account");
        if (adminState != null && account != null) {
//            int state = Integer.parseInt(adminState);
            if (adminState == "启用") {
                boolean flag = userService.changeSTate("禁用", account);
                if (flag == true) {
                    response.getWriter().write("修改成功");
                } else {
                    response.getWriter().write("修改失败");
                }
            }
            if (adminState == "禁用") {
                boolean flag = userService.changeSTate("启用", account);
                if (flag == true) {
                    response.getWriter().write("修改成功");
                } else {
                    response.getWriter().write("修改失败");
                }
            }
        }
        //用户删除
        if(adminStates!= null && account != null){
//            int newAdminState=Integer.parseInt(adminStates);
            if(adminStates=="启用"||adminStates=="禁用"){
                boolean flag = userService.changeSTate("已删除",account);
                if (flag == true) {
                    response.getWriter().write("删除成功");
                } else {
                    response.getWriter().write("删除失败");
                }
            }
        }
    }

    //重置密码
    public void replacement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String account = request.getParameter("account");
        if(account != null){
            boolean flag = userService.replacement(account);
            if (flag == true){
                response.getWriter().write("重置密码成功");
            }else{
                response.getWriter().write("重置密码失败");
            }
        }
    }

    //修改信息
    public void modification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String account = request.getParameter("account");
        String departmentID = request.getParameter("departmentID");
        String roleId = request.getParameter("roleId");
        if (account!=null&&departmentID!=null&&roleId!=null){
//            int newDepartmentID = Integer.parseInt(departmentID);
//            int newRoleId = Integer.parseInt(roleId);
            boolean flag = userService.modification(account,departmentID,roleId);
            if (flag == true){
                response.getWriter().write("修改成功");
            }else{
                response.getWriter().write("修改失败");
            }
        }
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String adminName = request.getParameter("adminName");
        String pwd = request.getParameter("pwd");
        String departmentID = request.getParameter("departmentID");
        String roleId = request.getParameter("roleId");
        String confirmPwd = request.getParameter("confirmPwd");
        int maxCount = userService.maxCount()+1;
        String newAccount = "xm00" + maxCount;
        Date date = new Date();
        boolean flag = userService.insert(newAccount,pwd,departmentID,roleId,adminName,date);
        if (flag == true){
            response.getWriter().write("新增人员成功");
        }
        request.setAttribute("newAdminName",adminName);
        request.setAttribute("newPwd",pwd);
        request.setAttribute("newDepartmentID",departmentID);
        request.setAttribute("newRoleId",roleId);
        request.setAttribute("newConfirmPwd",confirmPwd);
    }
}



