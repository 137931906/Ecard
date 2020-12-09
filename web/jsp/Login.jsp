<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%--<%@ page import="com.cykj.auc.bean.Admin" %>--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<%
    Object notLogin =request.getAttribute("notLogin");
    if (notLogin!=null){
        out.write("<script>alert('您还未登录')</script>");
    }
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>>
    <script src="${pageContext.request.contextPath}/js/demo.js" charset="UTF-8" ></script>
    <title></title>
</head>
<style type="text/css">
    html{
        margin: 0;
        padding: 0;
    }
    .loginbox {
        width: 22vw;
        height: 351px;
        box-shadow: 1px 1px 1px 1px #888;
        border-radius: 11px;
        position: fixed;
        top: -43px;
        left: 406px;
        background: #fff;
        opacity: 0.7;
        margin: 200px;
        padding-top: 45px;
    }

    .center {
        text-align: center;
    }

    .padding {
        padding: 15px;

    }

    input {
        border-radius: 9px;
    }

    .tab {
        border-bottom: solid #eee 3px;
        width: 103px;
        margin: 0 auto;
        padding: 8px;
    }

    .position_PassWord {

        position: absolute;
        right: 140px;
        margin-top: -16px;
    }

    .button {
        margin: 19px;
        padding: 7px;
        width: 215px;
        color: #fff;
        background: #ff6600;
        border-radius: 9px;
    }
    a{
        color: #888;
    }
    .topBox{
        background: #8888;
        height: 40px;
        text-align: right;
        padding-right: 78px;
        padding-top: 20px;
        margin-top: 42px;
    }
    .background{
        background: url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603879594593&di=596ce1afd02db4194be7b0be29431d77&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201510%2F01%2F20151001194727_tWMwB.thumb.700_0.jpeg") no-repeat;

        background-size:100% 100%;
    }
</style>
<body>
<div id=" " style="position: fixed;">


</div>
<input id="path" type="hidden" value="${pageContext.request.contextPath}">
<form action="loginServlet" method="post">
    <div class="background" style="    width: 100vw; height: 100vh;">
        <div id="  " class="loginbox">
            <div class="tab">
                后台登入界面
            </div>
            <div class="center padding">
                账号：<input type="text" name="account" id="account"/>
            </div>
            <div class="center padding" style="margin-bottom: 16px;">
                密码：<input type="password" name="pwd" id="pwd"/>
            </div>

            <div style="    text-align: center;">
                <div>
                    验证码：<input placeholder="请输入验证码" id="vCode" name="pwd" type="text" >
                </div>
                <img id="vImg" src="${pageContext.request.contextPath}/verifyCodeServlet" onclick="changeImg()">
                <span ONCLICK="changeImg()">看不清请刷新</span>
            </div>
            <div class="center padding" style="margin-left: 39px;" id="">
                <input type="button" value="登录" onclick="jq_ajax()" class="button">
            </div>
<%--            <div class="padding position_PassWord">--%>
<%--                <a href="">忘记密码</a>--%>
<%--            </div>--%>
<%--            <div class="topBox">--%>
<%--                <a href="Reg.html">注册新账号</a>--%>
<%--            </div>--%>
        </div>
    </div>
</form>
</body>
<script>

</script>

</html>
