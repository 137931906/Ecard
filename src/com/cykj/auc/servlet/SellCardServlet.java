package com.cykj.auc.servlet;

import com.cykj.auc.bean.Patient;
import com.cykj.auc.service.SellCardService;
import com.cykj.auc.util.ObjectFactory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sellCardServlet")
public class SellCardServlet extends BaseServlet {

    SellCardService sellCardService = (SellCardService) ObjectFactory.newInstance("com.cykj.auc.service.impl.SellCardServiceImpl");

    //售卡页面
    public String sellCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "jsp/SellCard_list.jsp";
    }

    //售卡功能
    public void sell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String birthplace = request.getParameter("birthplace");
        String idNumber = request.getParameter("idNumber");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
        String saveMoney = request.getParameter("saveMoney");
        String cardNumber = request.getParameter("cardNumber");
        System.out.println(name + "==" + age + "==" + sex + "==" + birthplace + "==" + idNumber + "==" + telephone + "==" + address + "==" + saveMoney + "==" + cardNumber);
        boolean flag1 = sellCardService.count(cardNumber);
        if (flag1 == true){
            boolean flag = sellCardService.getCardService(name,age,Integer.parseInt(sex),birthplace,idNumber,telephone,address,Integer.parseInt(saveMoney),cardNumber);
            if (flag = true){
                response.getWriter().write("领卡成功");
            }else {
                response.getWriter().write("领卡失败");
            }
        }else {
            response.getWriter().write("卡号不存在");
        }

    }

    //退卡界面
    public String cancelCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("jsp/CancelCard_list.jsp");
        return "jsp/CancelCard_list.jsp";
    }

    //读卡功能
    public void readCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String read = request.getParameter("read");
        System.out.println(read);
        List<Patient> patients = sellCardService.cancel(read);
        if (patients.size() == 0){
            response.getWriter().write("卡号不存在");
        }else {
            Gson gson = new Gson();
            request. getSession(). setAttribute("patients",patients);
            request. setAttribute("read",read);
            response. getWriter().write(gson.toJson(patients));
        }
    }

    //退卡
    public void quitcard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String read = request.getParameter("read");
        boolean n = sellCardService.quitCard(read);
        if (n == false){
            response.getWriter().write("退卡失败");
        }else{
            response.getWriter().write("退卡成功");
        }
    }

   //换卡界面
    public String changeCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        return "jsp/ChangeCard_list.jsp";
    }
    //换卡
    public void changecard (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String read = request.getParameter("read");
        String cardNum = request.getParameter("cardNum");
        boolean n = sellCardService.changeCard(read,cardNum);
        if (n == false){
            response.getWriter().write("换卡失败");
        }else{
            response.getWriter().write("换卡成功");
        }
    }
}
