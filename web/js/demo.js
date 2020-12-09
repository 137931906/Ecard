/**
 * 登录
 */

//登入
function jq_ajax() {

    var path = $("#path").val();
    var account = $("#account").val();
    var pwd = $("#pwd").val();
    var vCode = $("#vCode").val();

    $.ajax({
        url: path + "/loginServlet?methodName=login",
        type: "post",
        data: "account=" + account + "&pwd=" + pwd + "&vCode=" + vCode,
        datatype: "text",
        beforeSend: function () {
            $("[value='登入']").prop("disabled", "disabled");
            if (account == null || account.length < 5) {
                alert('无此账号');
                return false;
            }
            return true;
        },
        success: function (data) {
            alert(data);
            if (data == "登入成功") {
                location.href = path + "/menuServlet?methodName=findMenus";
            }
        },
        error: function () {
            alert('网络繁忙');
        },
        complete: function () {
            // alert('ajax执行完毕');
            $("[value='登入']").prop("disabled", "false");
        }
    })
}

//新增人员显示
function insertDiv(node) {
    $("#amends").css("display","block");
}

//隐藏人员显示
function hide(node) {
    $("#amends").css("display","none");
}


/**
 * 修改验证码图片
 */
function changeImg() {
    var path = $("#path").val();
    var date = new Date();

    //刷新验证码
    var img = document.getElementById('vImg');
    img.src = path+"/verifyCodeServlet?date="+date;
    debugger
}

//修改禁用状态
function changeAdminState(node) {
    var path = $("#path").val();
    var account = $(node).next().val();
    var adminState = node.parentNode.parentNode.children[4].innerHTML;
    console.log(adminState);
    $.ajax({
        url: path + "/userServlet?methodName=changeState",
        type: "post",
        data: "adminState=" + adminState + "&account=" + account,
        dataType: "text",
        beforeSend: function () {
            if (adminState == "禁用") {
                var result  = confirm("是否确定启用该用户");
                if (result) {
                    return true;
                } else {
                    return false;
                }
            }
            if (adminState == "启用") {
                var results  = confirm("是否确定禁用该用户");
                if (results) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        success: function (data) {
            alert(data);
            if (data == "修改成功") {
                if (adminState == "禁用") {
                    node.parentNode.parentNode.children[4].innerHTML = 1;
                    if (node.parentNode.parentNode.children[4].innerHTML = 1) {
                        $(node).val("禁用");
                    } else {
                        $(node).val("启用");
                    }
                }
                if (adminState == "启用") {
                    node.parentNode.parentNode.children[4].innerHTML = 2;
                    if (node.parentNode.parentNode.children[4].innerHTML = 2) {
                        $(node).val("启用");
                    } else {
                        $(node).val("禁用");
                    }
                }
            }
        }
    })
}
//删除
function deleteUser(node) {
    var path = $("#path").val();
    var account = $(node).next().val();
    var adminStates = node.parentNode.parentNode.children[4].innerHTML;
    $.ajax({
        url:path+"/userServlet?methodName=changeState",
        type:"post",
        data:"adminStates="+adminStates+"&account="+account+"",
        dataType:"text",
        beforeSend:function(){
            var result  = confirm("是否确定删除该用户");
            if (result){
                return true;
            }else {
                return false;
            }
        },
        success:function (data) {
            alert(data);
            if (data == "删除成功"){
                alert(data);
                node.parentNode.parentNode.children[4].innerHTML=3;
                node.parentNode.parentNode.children[5].innerHTML="";
            }
        }
    })
}
//重置
function reset(node) {
    var path = $("#path").val();
    var account = $(node).next().val();
    $.ajax({
        url:path+"/userServlet?methodName=replacement",
        type:"post",
        data:"account="+account+"",
        dataType:"text",
        beforeSend:function(){
            var result  = confirm("重置密码为123456");
            if (result){
                return true;
            }else {
                return false;
            }
        },
        success:function (data) {
            alert(data);
        }
    })
}


//修改隐藏域显示
var adminName=null;
var departmentID=null;
var roleId=null;
var account;
function recompose(node) {

    $("#amend").css("display","block");
    account=$(node).next().val();
    adminName= node.parentNode.parentNode.children[1].innerHTML;
    $("#inputName").attr("value",adminName);//显示名字框
    departmentID=node.parentNode.parentNode.children[2].innerHTML;
    roleId=node.parentNode.parentNode.children[3].innerHTML;
    console.log(adminName);
    console.log(departmentID);
    console.log(roleId);
}

//修改返回
function fanHui(node) {
    $("#amend").css("display","none");
}

//修改提交按钮
function modification(node) {

    var path = $("#path").val();
    var departmentID =$('#departmentID').val();
    var roleId = $('#roleId').val();
    $.ajax({
        url:path+"/userServlet?methodName=modification",
        type:"post",
        data:"account="+account+"&adminName="+adminName+"&departmentID="+departmentID+"&roleId="+roleId+"",
        dataType:"text",
        beforeSend:function(){
            if (account!= null && adminName != null && departmentID != null && roleId != null){
                var result  = confirm("是否确定修改");
                if (result){
                    return true;
                }else {
                    return false;
                }
            }
        },
        success:function (data) {
            alert(data);
            location=path + "/userServlet?methodName=info&curPage=1";//刷新页面
        }
    })
}

//新增人员提交
function insert() {
    // debugger
    alert("进来了")
    var path = $("#path").val();//拿到jSP隐藏域路径
    // var name = $("#name").val();
    // var pwd = $("#pwd").val();
    // var confirmPwd = $("#confirmPwd").val();
    // var office = $("#office").val();
    // var roles = $("#roles").val();
    $.ajax({
        url: path + "/userServlet?methodName=insert",
        type: "post",
        data: "adminName=" + $("#name").val() + "&pwd=" + $("#pwd").val() + "&departmentID=" + $("#office").val() + "&roleId=" + $("#roles").val() + "&confirmPwd=" + $("#confirmPwd").val() + "",
        dataType: "text",
        beforeSend: function () {
            if (!$("#name").val().length) {
                alert("名字不可为空");
                return false;
            } else if (!$("#pwd").val().length||!$("#confirmPwd").val().length) {
                alert("密码不可为空");
                return false;
            }else if ($("#pwd").val() != $("#confirmPwd").val()) {
                alert("2次密码不相同");
                return false;
            } else if ($("#pwd").val().length < 5) {
                alert("密码最少为五位数");
                return false;
            }  else if ($("#office").val() == 0) {
                alert("请选择科室");
                return false;
            } else if ($("#roles").val() == 0) {
                alert("请选择角色");
                return false;
            } else {
                return true;
            }
        },
        success: function (data) {
            if (data == "新增人员成功") {
                alert("新增人员成功");
                location=path + "/userServlet?methodName=info&curPage=1";//刷新页面
            }
        },
    })
}




