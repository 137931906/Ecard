<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/29
  Time: 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理界面</title>
</head>
<link rel="stylesheet" href="<%=path%>/css/Commodity.css">
<body>
<table align="center" id="head">
    <tr align="left">
        <td>账户：</td>
        <td><input type="text"></td>
        <td>电话：</td>
        <td><input type="text"></td>
        <td>状态：</td>
        <td>
            <select>
                <option value="none">暂无</option>
                <option value="online">启用</option>
            </select>
        </td>
    </tr>
    <tr align="left">
        <td>创建时间：</td>
        <td colspan="2">
            <input type="date">
            至
            <input type="date">
        </td>
        <td align="center" colspan="3"><input type="submit" value="搜索" id="check_button"></td>
    </tr>

</table>
<table align="center" id="foot" border="1">
    <tr class="table_footHead" align="center">
        <td>选择</td>
        <td>ID</td>
        <td>商品名</td>
        <td>类型</td>
        <td>现价</td>
        <td>原价</td>
        <td>数量</td>
        <td>状态</td>
        <td>操作</td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>
    <tr align="center">
        <td><input type="checkbox"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>竞价中</td>
        <td>
            <input type="submit" value="删除">
            <input type="submit" value="修改">
            <input type="submit" value="详情">
        </td>
    </tr>

</table>

<div align="center" style="margin-top: 10px">
    <input type="submit" value="上一页">1/1<input type="submit" value="上一页">
</div>

</body>
</html>
