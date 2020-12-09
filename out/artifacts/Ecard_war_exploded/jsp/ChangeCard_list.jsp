<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2020/11/24
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>换卡</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/CardDemo.js" ></script>
    <style>
        *{
            margin:0px;
            padding:0px;
        }
        html,body{
            height: 100%;
            width: 100%;
        }
        .Div{
            /*脱离文档流,相对于整个界面*/
            position: absolute;
            width:100%;
            height: 80%;
        }
    </style>
</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/sellCardServlet?methodName=changeCard" method="post">
<div class="Div">
    <form>
        <table width="100%">
            <tr  height=40px align="center">
                <td colspan="8" align="center"><h1>退卡</h1></td>
            </tr>
            <tr  height=40px align="center">
                <td width="12.5%"  colspan="3">请输入卡号：</td>
                <td width="12.5%"  colspan="4">
                    <input  type="text" id="read" name="read" placeholder="请输入卡号" style="width: 100%;height: 80%" >
                </td>
                <td width="12.5%" ><input type="button" value="读卡"  style="width: 60%;height: 100%" onclick="ready()"></td>
            </tr>
            <tr  height=40px align="center">
                <td width="12.5%">姓名：</td>
                <td width="12.5%"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="name" name="name"></td>
                <td width="12.5%">性别：</td>
                <td width="12.5%"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="sex" name="sex"></td>
                <td width="12.5%">年龄：</td>
                <td width="12.5%"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="age" name="age"></td>
                <td width="12.5%">籍贯：</td>
                <td width="12.5%"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="np" name="np"></td>
            </tr>
            <tr  height=40px align="center">
                <td width="12.5%">证件号码：</td>
                <td width="12.5%" colspan="3"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="number" name="number"></td>
                <td width="12.5%">联系电话：</td>
                <td width="12.5%" colspan="3"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="phone" name="phone"></td>
            </tr>
            <tr  height=40px align="center">
                <td width="12.5%" >现住址：</td>
                <td width="12.5%" colspan="7"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="address" name="address"></td>
            </tr>
            <tr  height=40px align="center">
                <td width="12.5%" >卡余额：</td>
                <td width="12.5%" colspan="3"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" id="balance" name="balance"></td>
                <td width="12.5%">卡押金：</td>
                <td width="12.5%" colspan="3"><input style="width: 100%;height: 80%" disabled ="disabled" type="text" value="5元"></td>

            </tr>
            <tr  height=40px align="center">
                <td width="12.5%">请输入新卡号：</td>
                <td width="12.5%" colspan="3"><input style="width: 100%;height: 80%" type="text" id="cardNum"></td>
                <td width="12.5%" colspan="3" ><input type="button" value="换卡"  style="width: 10%;height: 100%" onclick="changeCard()"></td>
            </tr>
        </table>
    </form>
</div>
</form>
</body>
</html>
