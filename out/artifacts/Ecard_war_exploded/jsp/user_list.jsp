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
    <title>用户信息管理</title>
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
        .user_panel{
            width: 98%;
        }
        .user_panel_title{
            background-color: #60707F;
            color: #E4E4E4;
            font-size: 14px;
            height: 35px;
        }
        .user_panel_info{
            font-size: 14px;
            color: #888888;
            height: 35px;
        }
        .btn{
            width: 80px;
            height: 30px;
            border: 1px solid #60707F;
            color: #60707F;
            background-color: white;
        }

        /*字体颜色*/
        .font{
            color: #888888;
            font-size: 14px;
        }

        .amend{
            float: left;
            width: 30%;
            height: 68%;
            background-color: pink;
            text-align: center;
            font-size: 18px;
            line-height: 68px;
            display: none;
            position: absolute;
            left: 35%;
            top: 18%;
        }

        .amends{
            float: left;
            width: 50%;
            height: 68%;
            background-color: pink;
            text-align: center;
            font-size: 18px;
            line-height: 38px;
            display: none;
            position: absolute;
            left: 35%;
            top: 18%;
        }

    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo.js"></script>

    <div class="amend" id="amend" >
        <h2>修改人员</h2>
        <span>人员姓名：</span>
        <input id="inputName"  type="text"value="" disabled>
        <br>
        <span>所属科室：</span>
        <select id = "departmentID" name="department">
            <option value="内科">内科</option>
            <option value="外科">外科</option>
        </select>
        <br>
        <span>所属角色：</span>
        <select id = "roleId" name="roleId">
            <option value="管理员">管理员</option>
            <option value="收费员">收费员</option>
        </select>
        <br>
        <input type="button" onclick="modification(this)" value="提交">
        <input type="button"  onclick="fanHui(this)" value="返回">
    </div>

    <div class="amends" id="amends" >
        <h2>新增人员</h2>
        <span>人员姓名：</span>
        <input id="name" type="text" >
        <br>
        <span>输入密码：</span>
        <input id="pwd" type="password" >
        <br>
        <span>确认密码：</span>
        <input id="confirmPwd" type="password" >
        <br>
        <span>所属科室：</span>
        <select id = "office" name="office">
            <option value="内科">内科</option>
            <option value="外科">外科</option>
        </select>
        <br>
        <span>所属角色：</span>
        <select id = "roles" name="roles">
            <option value="管理员">管理员</option>
            <option value="收费员">收费员</option>
        </select>
        <br>
        <input type="button" onclick="insert(this)" value="提交">
        <input type="button"  onclick="hide(this)" value="返回">
    </div>




</head>
<body>
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form action="${pageContext.request.contextPath}/userServlet?methodName=info&curPage=1" method="post">
    <table class="search_panel" align="center">
        <tr class="search_panel_info">
            <td width="8%"  >人员姓名:</td>
            <td width="20%">
                <input class="field" id="adminName" name="adminName" type="text" value="${adminName}"/>
            </td>
            <td width="8%">科室:</td>
            <td width="20%">
                <select class="field"  id="department" name="departmentId" >
                    <option value=""></option>
                    <option value="内科" <c:if test="${departmentId == '内科'}"> selected = "selected" </c:if> >内科</option>
                    <option value="外科" <c:if test="${departmentId == '外科'}"> selected = "selected" </c:if> >外科</option>
                    </select>
            </td>
        </tr>
        <tr class="search_panel_info">
            <td width="8%">角色:</td>
            <td width="20%">
                <select class="field"  id="role" name="roleId" >
                    <option value=""></option>
                    <option value="管理员" <c:if test="${roleId == '管理员'}"> selected = "selected" </c:if> >管理员</option>
                    <option value="收费员" <c:if test="${roleId == '收费员'}"> selected = "selected" </c:if> >收费员</option>
                    </select>
            </td>
            <td width="8%">状态:</td>
            <td width="20%">
                <select class="field"  id="selAdminState" name="adminState" >
                    <option value=""></option>
                    <option value="启用" <c:if test="${adminState == '启用'}"> selected = "selected" </c:if> >启用</option>
                    <option value="禁用" <c:if test="${adminState == '禁用'}"> selected = "selected" </c:if> >禁用</option>
                    </select>
            </td>
            <td width="8%" colspan="2">
                <input type="submit" class="btn" value="查询">
            </td>
            <td width="8%" colspan="2">
                <input type="button" class="btn" onclick="insertDiv(this)" value="新增人员">
                <input type="hidden" value="${admin.account}">
            </td>
        </tr>
    </table>
</form>
<table id="user_tab" class="user_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
    <tr class="user_panel_title" align="center">
        <td>序号</td>
        <td>人员姓名</td>
        <td>科室</td>
        <td>角色</td>
        <td>状态</td>
        <td width="300px">操作</td>
    </tr>
    <c:if test="${not empty pageBean}">
        <c:forEach items="${pageBean.list}" var="admin" varStatus="status">
            <tr class="user_panel_info" align="center">
                <td>${(pageBean.curPage-1)*pageBean.pageSize+(requestScope.offset+status.index+1)}</td>
<%--                <td>${admin.rn}</td>--%>
                <td>${admin.adminName}</td>
                <td>${admin.departmentID}</td>
                <td>${admin.roleId}</td>
                <td>${admin.adminState}</td>
                <td width="400px">
                    <c:if test="${admin.adminState!='禁用'}">
                        <input class="btn" type="button" onclick="recompose(this)" value="修改" />
                        <input type="hidden" value="${admin.account}">
                        <input class="btn" type="button" onclick="changeAdminState(this)" value="禁用"  />
                        <input type="hidden" value="${admin.account}">
                        <input class="btn" type="button" onclick="deleteUser(this)" value="删除" />
                        <input type="hidden" value="${admin.account}">
                        <input class="btn" type="button" onclick="reset(this)" value="重置密码" />
                        <input type="hidden" value="${admin.account}">
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
            <a href="${pageContext.request.contextPath}/userServlet?methodName=info&curPage=1&adminName=${adminName}&selAdminState=${adminState}&department=${departmentId}&role=${roleId}">首页</a>
            <a href="${pageContext.request.contextPath}/userServlet?methodName=info&curPage=${pageBean.prePage}&adminName=${adminName}&selAdminState=${adminState}&department=${departmentId}&role=${roleId}">上一页</a>
            ${pageBean.curPage}/${pageBean.totalPages}
            <a href="${pageContext.request.contextPath}/userServlet?methodName=info&curPage=${pageBean.nextPage}&adminName=${adminName}&selAdminState=${adminState}&department=${departmentId}&role=${roleId}">下一页</a>
            <a href="${pageContext.request.contextPath}/userServlet?methodName=info&curPage=${pageBean.totalPages}&adminName=${adminName}&selAdminState=${adminState}&department=${departmentId}&role=${roleId}" >尾页</a>
        </td>
    </tr>
</table>
</body>
</html>





































