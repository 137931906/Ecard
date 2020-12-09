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
    <title>售卡</title>
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
            position: fixed;
            width:60%;
            height: 80%;
            margin-left:20%;
        }

    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/CardDemo.js"></script>

</head>
<body>


<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/sellCardServlet?methodName=sellCard" method="past">
    <div class="Div">
    <form>
        <table class="search_panel" align="center">
            <tr></tr>
            <tr></tr>
            <tr height=40px>
                <td align="right">姓名：</td>
                <td><input type="text" style="width:150px;height:30px" name="name" id="name" value="${name}" ></td>
                <td align="right">年龄：</td>
                <td><input type="text" style="width:75px;height:30px" name="age" id="age" value="${age}" ></td>
            </tr>
            <tr height=40px>
                <td align="right">性别：</td>
                <td><input type = "radio" name = "sex" value="1"> 男 <input type = "radio" name = "sex" value="2"> 女</td>
                <td align="right" >籍贯：</td>
                <td>
                    <select type="text" style="width:100px;height:30px" name="birthplace" id="birthplace">
                        <option value="福建厦门" >福建厦门</option>
                        <option value="福建福州" >福建福州</option>
                        <option value="福建泉州" >福建泉州</option>
                        <option value="福建南平" >福建南平</option>
                        <option value="福建宁德" >福建宁德</option>
                    </select>
                </td>
            </tr>
            <tr height=40px>
                <td align="right">证件号码：</td>
                <td><input type="text" style="width:150px;height:30px" id="idNumber" name="idNumber" value="${idNumber}" ></td>
                <td align="right">联系电话：</td>
                <td><input type="text" style="width:150px;height:30px" id="telephone" name="telephone" value="${telephone}" ></td>
            </tr>
            <tr height=40px>
                <td align="right">现住址：</td>
                <td colspan="3"><input type="text" style="width:526px;height:30px" id="address" name="address" value="${address}"></td>
            </tr>
            <tr height=40px>
                <td align="right">预存金额：</td>
                <td><input type="text" style="width:150px;height:30px" id="saveMoney" name="saveMoney" value="${saveMoney}" ></td>
                <td align="right">卡号：</td>
                <td><input type="text" style="width:150px;height:30px" id="cardNumber" name="cardNumber" value="${cardNumber}"></td>
            </tr>
            <tr height=40px>
                <td></td>
                <td colspan="2" align="center">
                    <input type="button" style="width:120px;height:30px" value="出售" onclick="sellCard(this)">
                </td>
                <td></td>
            </tr>
        </table>
    </form>
    </div>
</form>
</body>
</html>
