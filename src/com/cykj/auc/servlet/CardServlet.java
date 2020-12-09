package com.cykj.auc.servlet;

import com.cykj.auc.bean.Card;
import com.cykj.auc.bean.PageBean;
import com.cykj.auc.service.CardService;
import com.cykj.auc.util.ObjectFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/cardServlet")
public class CardServlet extends BaseServlet {

    CardService cardService = (CardService) ObjectFactory.newInstance("com.cykj.auc.service.impl.CardServiceImpl");

    //卡入库表格，分页
    public String card(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String minCardNum = request.getParameter("minCardNum");
        String maxCardNum = request.getParameter("maxCardNum");
        String minInputTime = request.getParameter("minInputTime");
        String maxInputTime = request.getParameter("maxInputTime");
        String CardState = request.getParameter("CardState");

        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (minCardNum != null && !minCardNum.trim().equals("")) {
            condition.put("minCardNum", minCardNum);
        }
        if (maxCardNum != null && !maxCardNum.trim().equals("")) {
            condition.put("maxCardNum", maxCardNum);
        }
        if (minInputTime != null && !minInputTime.trim().equals("")) {
            condition.put("minInputTime", minInputTime);
        }
        if (maxInputTime != null && !maxInputTime.trim().equals("")) {
            condition.put("maxInputTime", maxInputTime);
        }
        if (CardState != null && !CardState.trim().equals("")) {
            condition.put("CardState", CardState);
        }
        PageBean<Card> pageBean = cardService.findCardByPage(condition, curPage, 5);
        request.setAttribute("minCardNum", minCardNum);
        request.setAttribute("maxCardNum", maxCardNum);
        request.setAttribute("minInputTime", minInputTime);
        request.setAttribute("maxInputTime", maxInputTime);
        request.setAttribute("CardState", CardState);
        request.setAttribute("pageBean", pageBean);
        return "jsp/card_list.jsp";
    }

    //卡注销表格，分页
    public String cardLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String minCardNum = request.getParameter("minCardNum");
        String maxCardNum = request.getParameter("maxCardNum");
        String CardState = request.getParameter("CardState");

        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (minCardNum != null && !minCardNum.trim().equals("")) {
            condition.put("minCardNum", minCardNum);
        }
        if (maxCardNum != null && !maxCardNum.trim().equals("")) {
            condition.put("maxCardNum", maxCardNum);
        }
        if (CardState != null && !CardState.trim().equals("")) {
            condition.put("CardState", CardState);
        }
        PageBean<Card> pageBean = cardService.findCardByPage(condition, curPage, 5);
        request.setAttribute("minCardNum", minCardNum);
        request.setAttribute("maxCardNum", maxCardNum);
        request.setAttribute("CardState", CardState);
        request.setAttribute("pageBean", pageBean);
        return "jsp/CardLogout_list.jsp";
    }

    //卡查询表格，分页
    public String cardInquire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        String minCardNum = request.getParameter("minCardNum");
        String maxCardNum = request.getParameter("maxCardNum");
        String CardState = request.getParameter("CardState");
        String cardNum = request.getParameter("cardNum");

        int curPage = Integer.parseInt(curPageStr);
        HashMap<String, Object> condition = new HashMap<>();
        if (minCardNum != null && !minCardNum.trim().equals("")) {
            condition.put("minCardNum", minCardNum);
        }
        if (CardState != null && !CardState.trim().equals("")) {
            condition.put("CardState", CardState);
        }
        if (cardNum != null && !cardNum.trim().equals("")) {
            condition.put("cardNum", cardNum);
        }
        PageBean<Card> pageBean = cardService.findCardByPage(condition, curPage, 5);
        request.setAttribute("minCardNum", minCardNum);
        request.setAttribute("maxCardNum", maxCardNum);
        request.setAttribute("CardState", CardState);
        request.setAttribute("cardNum",cardNum);
        request.setAttribute("pageBean", pageBean);
        return "jsp/CardQueries_list.jsp";
    }

    //卡入库
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cardPr = request.getParameter("cardPr");//前缀
        String finish = request.getParameter("finish");//数量
        System.out.println(cardPr+"===="+finish);
        int end = Integer.parseInt(finish);
        int start = cardService.maxCount().intValue() + 1;
        for (int i = start; i <= start + end - 1 ;i++){
            String cardNum = cardPr + i;
            cardService.insert(cardNum,cardPr,i);
        }
        response.getWriter().write("入库成功");
        request.setAttribute("cardPr",cardPr);
        request.setAttribute("finish",finish);
    }

    //卡删除
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cardId = request.getParameter("cardId");
        if(cardId != null){
                boolean flag = cardService.delete(Integer.parseInt(cardId));
            if (flag == true){
                response.getWriter().write("删除成功");
            }else{
                response.getWriter().write("删除失败");
            }
        }
    }

    //卡注销
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cardId = request.getParameter("cardId");
        if(cardId != null){
            boolean flag = cardService.logOut(Integer.parseInt(cardId));
            if (flag == true){
                response.getWriter().write("注销成功");
            }else{
                response.getWriter().write("注销失败");
            }
        }
    }

    //卡审批
//    public void approval(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String cardPr = request.getParameter("cardPr");
//        String gcNum = request.getParameter("gcNum");
//        String gcId = request.getParameter("gcId");
//        String adminId = request.getParameter("adminId");
//        String getCardId = request.getParameter("getCardId");
//        HashMap<String,Object> cond = new HashMap<>();
//        if (cardPr != null && cardPr.trim().equals("")){
//            cond.put("cardPr",cardPr);
//        }
//        int count = CardService.count(cond).intValue();
//        System.out.println("库存"+count);
//        if (count<Integer.parseInt(gcNum)){
//            response.getWriter().write("库存数量不足");
//        }else {
//            boolean flag = CardService.application(Integer.parseInt(getCardId),cardPr,Integer.parseInt(gcNum),gcId,Integer.parseInt(adminId));
//            if (flag = true){
//                response.getWriter().write("审核完成");
//            }else {
//                response.getWriter().write("审核失败");
//            }
//        }
//    }
}
