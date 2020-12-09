<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/4
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>卡片注销</title>
    <style>
        html,body{
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        /*搜索面板列表*/
        .search_panel{
            width: 80%;
        }
        .search_panel_info{
            font-size: 14px;
            color: #888888;
            height: 35px;
        }
        .field{
            width: 180px;
            height: 30px;
            color: #888888;
        }

        /*管理员界面列表*/
        .card_panel{
            width: 98%;
        }
        .card_panel_title{
            background-color: #60707F;
            color: #E4E4E4;
            font-size: 14px;
            height: 35px;
        }
        .card_panel_info{
            font-size: 14px;
            color: #888888;
            height: 35px;
        }
        .btns{
            width: 80px;
            height: 30px;
            border: 1px solid #60707F;
            color: #60707F;
            background-color: white;
        }

        .cards{
            float: left;
            width: 25%;
            height: 50%;
            background-color: pink;
            text-align: center;
            font-size: 18px;
            line-height: 38px;
            display: none;
            position: absolute;
            left: 40%;
            top: 18%;
        }



    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/CardDemo.js"></script>

</head>
<body>



<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/cardServlet?methodName=cardLogout&curPage=1" method="post">
    <table class="search_panel" align="center">
        <tr class="search_panel_info">
        <tr class="search_panel_info">
        <td width="8%">卡号:</td>
        <td colspan="3">
            <input name="minCardNum" type="text" value="${minCardNum}">
        至
        <input name="maxCardNum" type="text" value="${maxCardNum}">
    </td>
        </td>
        </tr>
        <tr class="search_panel_info">
            <td width="8%">卡状态:</td>
            <td width="20%">
                <select class="selCardState" id="selCardState" name="CardState">
                    <option value=""></option>
                    <option value="1" <c:if test="${CardState == '1'}"> selected = "selected" </c:if> >待销售</option>
                    <option value="2" <c:if test="${CardState == '2'}"> selected = "selected" </c:if> >已损坏</option>
                </select>
            </td>
            <td width="8%" colspan="2">
                <input type="submit" class="btns" value="查询">
            </td>
        </tr>
    </table>
</form>
<table id="user_tab" class="card_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
    <tr class="card_panel_title" align="center">
        <td>序号</td>
        <td>卡号</td>
        <td>卡状态</td>
        <td width="300px">操作</td>
    </tr>
    <c:if test="${not empty pageBean}">
        <c:forEach items="${pageBean.list}" var="card" varStatus="status">
            <tr class="card_panel_info" align="center">
                <td>${card.rn}</td>
                <td>${card.cardNum}</td>
                <td>${card.cardState}</td>
                <td width="400px">
                    <c:if test="${card.cardState!='已注销'}">
                    <input class="btns" type="submit" onclick="logOut(this)" value="注销">
                    <input id="cardId" type="hidden" value="${card.cardId}">
                    </c:if>
                </td>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<table class="function_panel" align="center">
    <tr>
        <td>
            <a href="${pageContext.request.contextPath}/cardServlet?methodName=cardLogout&curPage=1&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}&minInputTime=${minInputTime}&maxInputTime=${maxInputTime}&selCardState=${cardState}">首页</a>
            <a href="${pageContext.request.contextPath}/cardServlet?methodName=cardLogout&curPage=${pageBean.prePage}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}&minInputTime=${minInputTime}&maxInputTime=${maxInputTime}&selCardState=${cardState}">上一页</a>
            ${pageBean.curPage}/${pageBean.totalPages}
            <a href="${pageContext.request.contextPath}/cardServlet?methodName=cardLogout&curPage=${pageBean.nextPage}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}&minInputTime=${minInputTime}&maxInputTime=${maxInputTime}&selCardState=${cardState}">下一页</a>
            <a href="${pageContext.request.contextPath}/cardServlet?methodName=cardLogout&curPage=${pageBean.totalPages}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}&minInputTime=${minInputTime}&maxInputTime=${maxInputTime}&selCardState=${cardState}">尾页</a>
        </td>
    </tr>
</table>

</body>
</html>
