<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.cykj.auc.bean.Menu" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理系统主页</title>
</head>
<link rel="stylesheet" href="<%=path%>/css/HouTai.css">
<script src="<%=path%>/js/index.js"></script>
<body>
<div id="head">
    <b id="top_title">竞价管理系统</b>
    <a href="#">退出</a>
    <a href="#">欢迎 xxx</a>
    <a href="#">服务条款</a>
</div>
<div id="left">
    <%
        //使用HasMap获取菜单
        HashMap menuMap = (HashMap) request.getSession().getAttribute("menuMap");
        if (menuMap!=null){
            Iterator<String> iterator = menuMap.keySet().iterator();
            while (iterator.hasNext()) {
                String menuName = iterator.next();
                List<Menu> menus = (List<Menu>) menuMap.get(menuName);
                out.write("<div>");
                out.write("<ul><li>");
                out.write("h2 href=#' class='button' onclick='showOrHide(this)'>" + menuName + "</h2>");
                for (Menu menu : menus) {
                    out.write("<li><a title='" + menu.getUrl() + "' onclick  ");
                }
                out.write("</div>");
            }

        }
    %>
    <ul>
        <li>
            <h2 href="#" class="button" onclick="showOrHide(this)">后台管理</h2>
            <div class="button-hide" style="display: none;">
                <h3 href="#">会员管理</h3>
                <h3 href="#">商品分类管理</h3>
            </div>
        </li>
        <li>
            <h2 href="#" class="button" onclick="showOrHide(this)">前台管理</h2>
            <div class="button-hide" style="display: none;">
                <h3 href="#">商品管理</h3>
                <h3 href="#">个人信息管理</h3>
            </div>
        </li>
        <li><h2 href="#" class="button">用户管理</h2></li>
        <li><h2 href="#" class="button">商品管理</h2></li>
        <%--        <li><h2 href="#" class="button">商品流转</h2></li>--%>
    </ul>

</div>
<div id="right">
    <iframe src="${pageContext.request.contextPath}/userServlet?curPage=1"></iframe>
</div>


</body>
</html>
