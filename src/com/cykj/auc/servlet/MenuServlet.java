package com.cykj.auc.servlet;

import com.cykj.auc.bean.Menu;
import com.cykj.auc.service.MenuService;
import com.cykj.auc.util.ObjectFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/menuServlet")
public class MenuServlet extends BaseServlet {

    MenuService menuService = (MenuService) ObjectFactory.newInstance("com.cykj.auc.service.impl.MenuServiceImpl");

    public void findMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        System.out.println("菜单请求");
        //菜单栏
        HashMap<String, List<Menu>> menuMap = menuService.findMenuMap(0);
        //数据存入request
        request.setAttribute("menuMap",menuMap);
        //携参跳转
        request.getRequestDispatcher("jsp/Backstage.jsp").forward(request, response);
    }
}

