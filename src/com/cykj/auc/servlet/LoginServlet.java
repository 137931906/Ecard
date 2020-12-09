package com.cykj.auc.servlet;

import com.cykj.auc.bean.Admin;
import com.cykj.auc.service.AdminService;
import com.cykj.auc.util.ObjectFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet({"/loginServlet"})
public class LoginServlet extends BaseServlet {

    //使用工厂创建实例化，使整个项目中只有一个实例
    AdminService adminService = (AdminService) ObjectFactory.newInstance("com.cykj.auc.service.impl.AdminServiceImpl");

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("account");//获取表单信息--依据参数名返回参数值
        String pwd = request.getParameter("pwd");
        System.out.println(account + "====" + pwd);
        Admin admin = adminService.login(account, pwd);
        String vCode = request.getParameter("vCode");
        String vCodeServer = (String) request.getSession().getAttribute("vCode");
        request.getSession().removeAttribute("vCode");

        if (vCode.equalsIgnoreCase(vCodeServer)) {
            if (admin != null) {
                request.getSession().setAttribute("admin", admin);
                response.getWriter().write("登入成功");
            } else {
                response.getWriter().write("登入失败");
            }
        }else{
            response.getWriter().write("验证码输入错误");
        }

    }

}
