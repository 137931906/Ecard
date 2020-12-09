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
    <title>卡审批</title>
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

    <div class="cards" id="cards">
        <h2>领卡审核</h2>
        <span>申请人</span>
        <span>
            <input disabled ="disabled" type="text" id="Name" name="Name" value="" >
        </span>
        <br>
        <span>申请时间</span>
        <span>
            <input id="cardTime" htype="text" disabled >
        </span>
        <br>
        <span>申请数量</span>
        <span>
            <input id="gcNum" htype="text" disabled >
        </span>
        <br>
        <span>输入前缀</span>
        <input type="text" id="cardPr">
        <br>
        <input type="button" class="btn" onclick="approval(this)" value="审核通过">
        <input type="button" class="btn" onclick="getBack(this)" value="返回">
    </div>
    <div class="cards">
        <h2>查看</h2>
        <span>申请人</span>
        <span>申请时间</span>
        <span>申请数量</span>
        <span>审核人</span>
        <span>审核时间</span>
        <span>领用卡号</span>
    </div>
</head>
<body>


<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/getCardServlet?methodName=examine&curPage=1" method="past">
    <table class="search_panel" align="center">
        <tr class="search_panel_info">
        <tr class="search_panel_info">
            <td width="8%">申请人:</td>
            <td width="20%">
                <select name="selCardState">
                    <option value=""></option>
                    <option value="1" <c:if test="${gcState == '1'}"> selected = "selected" </c:if> >管理员</option>
                    <option value="2" <c:if test="${gcState == '2'}"> selected = "selected" </c:if> >收费员</option>
                </select>
            </td>
        </tr>
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
        </tr>
    </table>
</form>
<table id="user_tab" class="card_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
    <tr class="card_panel_title" align="center">
        <td>序号</td>
        <td>申请时间</td>
        <td>申请人</td>
        <td>申请数量</td>
        <td>审核人</td>
        <td>审核时间</td>
        <td>状态</td>
        <td width="300px">操作</td>
    </tr>
    <c:if test="${not empty pageBean}">
        <c:forEach items="${pageBean.list}" var="GetCard" varStatus="status">
            <tr class="card_panel_info" align="center">
                <td>${GetCard.rn}</td>
                <td>${GetCard.gcTime}</td>
                <td>${GetCard.gcId}</td>
                <td>${GetCard.gcNum}</td>
                <td>${GetCard.userId}</td>
                <td>${GetCard.apprTime}</td>
                <td>${GetCard.gcState}</td>
                <td width="400px">
                    <input class="btns" type="submit" onclick="verify(this)" value="审核">
                    <input type="hidden" value="${GetCard.id}">
                    <input class="btns" type="submit" onclick="" value="查看">
                </td>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<table class="function_panel" align="center">
    <tr>
        <td>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=examine&curPage=1&cardNum=${cardNum}&state=${state}&inputTime=${inputTime}">首页</a>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=examine&curPage=${pageBean.prePage}&cardNum=${cardNum}&state=${state}&inputTime=${inputTime}">上一页</a>
            ${pageBean.curPage}/${pageBean.totalPages}
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=examine&curPage=${pageBean.nextPage}&cardNum=${cardNum}&state=${state}&inputTime=${inputTime}">下一页</a>
            <a href="${pageContext.request.contextPath}/getCardServlet?methodName=examine&curPage=${pageBean.totalPages}&cardNum=${cardNum}&state=${state}&inputTime=${inputTime}" >尾页</a>
        </td>
    </tr>
</table>

</body>
</html>
