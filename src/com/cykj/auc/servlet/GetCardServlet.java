package com.cykj.auc.servlet;


import com.cykj.auc.bean.Admin;
import com.cykj.auc.bean.Card;
import com.cykj.auc.bean.GetCard;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.service.GetCardService;
import com.cykj.auc.util.ObjectFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@WebServlet("/getCardServlet")
public class GetCardServlet extends BaseServlet {

    GetCardService getCardService = (GetCardService) ObjectFactory.newInstance("com.cykj.auc.service.impl.GetCardServiceImpl");

    //领卡表格，分页
    public String card(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String cardNum = request.getParameter("cardNum");
        String cardState = request.getParameter("cardState");
        String inputTime = request.getParameter("inputTime");
        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (cardNum != null && !cardNum.trim().equals("")) {
            condition.put("cardNum", cardNum);
        }
        if (cardState != null && !cardState.trim().equals("")) {
            condition.put("cardState", cardState);
        }
        if (inputTime != null && !inputTime.trim().equals("")) {
            condition.put("inputTime", inputTime);
        }
        PageBean<GetCard> pageBean = getCardService.findCardByPage(condition, curPage, 5);
        request.setAttribute("cardNum", cardNum);
        request.setAttribute("cardState", cardState);
        request.setAttribute("inputTime", inputTime);
        request.setAttribute("pageBean", pageBean);
        return "jsp/CardFor_list.jsp";
    }

//卡申请
    public void apply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String gcTime = request.getParameter("gcTime");
        String gcNum = request.getParameter("gcNum");
        String gcId = request.getParameter("gcId");
        int newgcNum = Integer.parseInt(gcNum);

            boolean flag = getCardService.apply(newgcNum, gcId);
            if (flag == true){
                response.getWriter().write("申请成功");
            }else{
                response.getWriter().write("申请失败");
            }

    }

    //卡审批表格，分页
    public String examine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String gcId = request.getParameter("gcId");
        String gcState = request.getParameter("gcState");
        String gcTime = request.getParameter("gcTime");

        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (gcId != null && !gcId.trim().equals("")) {
            condition.put("gcId", gcId);
        }
        if (gcState != null && !gcState.trim().equals("")) {
            condition.put("gcState", gcState);
        }
        if (gcTime != null && !gcTime.trim().equals("")) {
            condition.put("gcTime", gcTime);
        }
        PageBean<GetCard> pageBean = getCardService.findCardByPage(condition, curPage, 5);
        request.setAttribute("gcId", gcId);
        request.setAttribute("gcState", gcState);
        request.setAttribute("gcTime", gcTime);
        request.setAttribute("pageBean", pageBean);
        return "jsp/CardCollecting _list.jsp";
    }

    //卡审批
    public void cardExamine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardPr = request.getParameter("cardPr");
        String gcNum = request.getParameter("gcNum");
        String id = request.getParameter("id");
        Admin admin= (Admin) request.getSession().getAttribute("admin");

        int checkid=admin.getAdminId().intValue();
        String checkName=admin.getAdminName();
        String gcId = request.getParameter("gcId");
        System.out.println("前缀"+cardPr+"数量"+gcNum+"申请人"+gcId+"审核人"+checkName);
        HashMap<String,Object> cond = new HashMap<>();
        if (cardPr != null && cardPr.trim().equals("")){
            cond.put("cardPr",cardPr);
        }
        int count = getCardService.count(cond).intValue();
        System.out.println("库存"+count);
        if (count<Integer.parseInt(gcNum)){
            response.getWriter().write("库存数量不足");
        }else {
            boolean flag = getCardService.cardList(cardPr,Integer.parseInt(gcNum),checkid,Integer.parseInt(id));
            System.out.println(flag);
            if (flag = true){
                response.getWriter().write("审核完成");
            }else {
                response.getWriter().write("审核失败");
            }
        }
    }

}
