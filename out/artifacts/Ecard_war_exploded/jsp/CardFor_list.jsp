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
    <title>卡领取</title>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        /*搜索面板列表*/
        .search_panel {
            width: 80%;
        }

        .search_panel_info {
            font-size: 14px;
            color: #888888;
            height: 35px;
        }

        .field {
            width: 180px;
            height: 30px;
            color: #888888;
        }

        /*管理员界面列表*/
        .card_panel {
            width: 98%;
        }

        .card_panel_title {
            background-color: #60707F;
            color: #E4E4E4;
            font-size: 14px;
            height: 35px;
        }

        .card_panel_info {
            font-size: 14px;
            color: #888888;
            height: 35px;
        }

        .btns {
            width: 80px;
            height: 30px;
            border: 1px solid #60707F;
            color: #60707F;
            background-color: white;
        }

        .cards {
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

        .cards {
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
    <div class="cards" id="cards">
        <form>
            <table width="50%">
                <h2>申请单</h2>
                <span>申请人：</span>
                <input value="${admin.adminName}" type="text" disabled id="gcId">
                <br>
                <span>申请时间：</span>
                <input type="text" disabled id="gcTime">
                <br>
                <span>申请卡数量：</span>
                <input type="text" id="gcNum">
                <span>张</span>
                <br>
                <input type="button" class="btn" onclick="putIn(this)" value="提交">
                <input type="button" class="btn" onclick="cardHide(this)" value="返回">
            </table>
        </form>
    </div>
</head>
<body>


<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/getCardServlet?methodName=card&curPage=1" method="post">
    <table class="search_panel" align="center">
        <tr class="search_panel_info">
        <tr class="search_panel_info">
            <td width="8%">申请日期:</td>
            <td colspan="3">
                <input name="minInputTime" type="date" value="${minInputTime}">
                至
                <input name="maxInputTime" type="date" value="${maxInputTime}">
            </td>
        </tr>
        <tr class="search_panel_info">
            <td width="8%">审核状态:</td>
            <td width="20%">
                <select name="selCardState">
                    <option value=""></option>
                    <option value="1" <c:if test="${gcState == '1'}"> selected = "selected" </c:if> >待审核</option>
                    <option value="2" <c:if test="${gcState == '2'}"> selected = "selected" </c:if> >已审核</option>
                </select>
            </td>
            <td width="8%" colspan="2">
                <input type="submit" class="btns" value="查询">
            </td>
            <td width="8%" colspan="2">
                <input type="button" class="btns" onclick="cardShow(this)" value="新申请">
            </td>
        </tr>
    </table>
</form>
<table id="user_tab" class="card_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
    <tr class="card_panel_title" align="center">
        <td>序号</td>
        <td>申请日期</td>
        <td>申请卡数量</td>
        <td>申请人</td>
        <td>审核状态</td>
        <td>审核人</td>
        <td>审核时间</td>
        <td width="300px">操作</td>
    </tr>
    <c:if test="${not empty pageBean}">
        <c:forEach items="${pageBean.list}" var="GetCard" varStatus="status">
            <tr class="card_panel_info" align="center">
                <td>${GetCard.rn}</td>
                <td>${GetCard.gcTime}</td>
                <td>${GetCard.gcNum}</td>
                <td>${GetCard.gcId}</td>
                <td>${GetCard.gcState}</td>
                <td>${GetCard.userId}</td>
                <td>${GetCard.apprTime}</td>
                <td width="400px">
                    <input class="btns" type="submit" onclick="" value="修改">

                </td>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<table class="function_panel" align="center">
    <tr>
        <td>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=card&curPage=1&selCardState=${state}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}">首页</a>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=card&curPage=${pageBean.prePage}&selCardState=${state}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}">上一页</a>
            ${pageBean.curPage}/${pageBean.totalPages}
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=card&curPage=${pageBean.nextPage}&selCardState=${state}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}">下一页</a>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=card&curPage=${pageBean.totalPages}&selCardState=${state}&minCardNum=${minCardNum}&maxCardNum=${maxCardNum}">尾页</a>
        </td>
    </tr>
</table>

</body>
</html>
