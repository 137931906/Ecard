<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.cykj.auc.bean.Menu" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/28
  Time: 3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <%
        String path = request.getContextPath();
        System.out.println(path);
        String acc=(String) request.getAttribute("acc");
    %>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js "></script>
    <link href=<%=path+"/css/Backstage.css"%> rel="stylesheet">
    <script src=<%=path+"/js/Backstage.js"%>></script>
</head>
<div id="header">
    <span>    后台管理系统</span><button id="btn1">修改密码</button>
    <strong>
    </strong><button id="btn2">退出</button>
</div>
<div id="aside">
    <div id="nav">
        <c:if test="${not empty menuMap}">
            <c:forEach items="${menuMap.keySet()}" var="menuName">
                <h2 onclick="showHidden(this)">${menuName}</h2>
                <div style="display: none">
                    <c:forEach items="${menuMap.get(menuName)}" var="nodeName">
                        <a title="${nodeName.getUrl()}"  onclick="changePath(this)">${nodeName.getMenuName()}</a>
                    </c:forEach>
                </div>
            </c:forEach>
        </c:if>

    </div>
</div>
<div id="info">
    <iframe id="iframe"></iframe>

</div>
<body>
<button ></button>
</body>
</html>






















<%--<%@ page import="java.util.HashMap" %>--%>
<%--<%@ page import="java.util.Iterator" %>--%>
<%--<%@ page import="com.cykj.auc.bean.Menu" %>--%>
<%--<%@ page import="java.util.List" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: lenovo--%>
<%--  Date: 2020/10/27--%>
<%--  Time: 15:53--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%--%>
<%--    String path = request.getContextPath();--%>
<%--%>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>后台管理系统主页</title>--%>
<%--</head>--%>
<%--<link rel="stylesheet" href="<%=path%>/css/HouTai.css">--%>
<%--<script src="<%=path%>/js/index.js"></script>--%>
<%--<body>--%>
<%--<div id="head">--%>
<%--    <b id="top_title">竞价管理系统</b>--%>
<%--    <a href="#">退出</a>--%>
<%--    <a href="#">欢迎 xxx</a>--%>
<%--    <a href="#">服务条款</a>--%>
<%--</div>--%>
<%--<div id="left">--%>
<%--    <%--%>
<%--        //使用HasMap获取菜单--%>
<%--        HashMap menuMap = (HashMap) request.getSession().getAttribute("menuMap");--%>
<%--        if (menuMap!=null){--%>
<%--            Iterator<String> iterator = menuMap.keySet().iterator();--%>
<%--            while (iterator.hasNext()) {--%>
<%--                String menuName = iterator.next();--%>
<%--                List<Menu> menus = (List<Menu>) menuMap.get(menuName);--%>
<%--                out.write("<div>");--%>
<%--                out.write("<ul><li>");--%>
<%--                out.write("h2 href=#' class='button' onclick='showOrHide(this)'>" + menuName + "</h2>");--%>
<%--                for (Menu menu : menus) {--%>
<%--                    out.write("<li><a title='" + menu.getUrl() + "' onclick  ");--%>
<%--                }--%>
<%--                out.write("</div>");--%>
<%--            }--%>

<%--        }--%>
<%--    %>--%>
<%--    <ul>--%>
<%--        <li>--%>
<%--            <h2 href="#" class="button" onclick="showOrHide(this)">后台管理</h2>--%>
<%--            <div class="button-hide" style="display: none;">--%>
<%--                <h3 href="#">用户管理</h3>--%>
<%--                <h3 href="#">个人信息管理</h3>--%>
<%--            </div>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <h2 href="#" class="button" onclick="showOrHide(this)">前台管理</h2>--%>
<%--            <div class="button-hide" style="display: none;">--%>
<%--                <h3 href="#">药品管理</h3>--%>
<%--                <h3 href="#">药品分类管理</h3>--%>
<%--            </div>--%>
<%--        </li>--%>
<%--&lt;%&ndash;        <li><h2 href="#" class="button">用户管理</h2></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <li><h2 href="#" class="button">药品管理</h2></li>&ndash;%&gt;--%>
<%--    </ul>--%>
<%--</div>--%>
<%--<div id="right">--%>
<%--    <iframe src="${pageContext.request.contextPath}/adminServlet?curPage=1"></iframe>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>








































