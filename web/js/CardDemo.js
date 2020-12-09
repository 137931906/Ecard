

//卡入库显示
function cardShow(node) {
    $("#cards").css("display","block");
}

//卡入库隐藏
function cardHide(node) {
    $("#cards").css("display","none");
}

function insert() {
    var past = $("#path").val();
    var path = $("#path").val();
    var cardPr = $("#cardPr").val();
    var finish = $("#finish").val();
    $.ajax({
        url:past+"/cardServlet?methodName=insert",
        type:"post",
        data:"cardPr="+cardPr+"&finish="+finish,
        dataType:"text",
        beforeSend:function(){

        },
        success:function (data) {
            alert(data);
            if (data=="入库成功"){
                $("#cards").css("display","none");
                location.href = past+"/cardServlet?methodName=card&curPage=1";
            }
        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })
}

//删除
function deleteCard(node) {
    var past = $("#path").val();
    var path = $("#path").val();
    var cardId = $("#cardId").val();
    var cardState = $("#cardState").val();
    var cardState = node.parentNode.parentNode.children[3].innerHTML;
    $.ajax({
        url:path+"/cardServlet?methodName=delete",
        type:"post",
        data:"cardId ="+cardId+"&cardState="+cardState+"",
        dataType:"text",
        beforeSend:function(){
            var result  = confirm("是否确定删除该卡");
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
                location.href = past+"/cardServlet?methodName=card&curPage=1";
                node.parentNode.parentNode.children[4].innerHTML="已删除";
            }
        }
    })
}

//卡注销
function logOut(node) {
    var past = $("#path").val();
    var path = $("#path").val();
    var cardId = $("#cardId").val();
    var cardState = $("#cardState").val();
    var cardState = node.parentNode.parentNode.children[2].innerHTML;
    console.log(cardId);
    $.ajax({
        url:path+"/cardServlet?methodName=logOut",
        type:"post",
        data:"cardId ="+cardId+"&cardState="+cardState+"",
        dataType:"text",
        beforeSend:function(){
            var result = confirm("是否确定注销该用户");
            if (result){
                return true;
            }else {
                return false;
            }
        },
        success:function (data) {
            alert(data);
            if (data == "注销成功"){
                alert(data);
                location.href = past+"/cardServlet?methodName=cardLogout&curPage=1";
                node.parentNode.parentNode.children[4].innerHTML='已注销';
            }
        }
    })
}

//卡领取显示
function cardShow(node) {
    $("#cards").css("display","block");
}

//卡领取隐藏
function cardHide(node) {
    $("#cards").css("display","none");
}

//领卡申请
function putIn(node){
    var past = $("#path").val();
    var path = $("#path").val();
    var gcId = $("#gcId").val();
    var gcNum = $("#gcNum").val();
    var gcTime = new Date();
    var year = gcTime.getFullYear();
    console.log(gcNum);
    var month = gcTime.getMonth()+1<10 ?"0"+(gcTime.getMonth()+1):(gcTime.getMonth()+2);//得到当前日子(多少号)
    var date = gcTime.getDate()< 10 ?"0"+gcTime.getDate():gcTime . getDate();
    var currentTime = year+"-"+month+"-"+date;
    $.ajax({
        url:path+"/getCardServlet?methodName=apply",
        type:"post",
        data:"gcId="+gcId+"&gcTime="+gcTime+"&gcNum="+gcNum+"",
        dataType:"text",
        beforeSend:function(){

        },
        success:function (data) {
            alert(data);
            if (data == "申请成功"){
                alert(data);
                location.href = past+"/getCardServlet?methodName=card&curPage=1";
            }
        }
    })
}

//领卡审批显示
var Id;
function verify(node) {
    Id = $(node).next().val();
    var num=node.parentNode.parentNode.children[3].innerHTML;
    $("#gcNum").val(num);
   gcId=node.parentNode.parentNode.children[2].innerHTML;
    $("#Name").val(gcId);
    var sqsj=node.parentNode.parentNode.children[1].innerHTML;
    $("#cardTime").val(sqsj);
    $("#cards").css("display","block");


}
//领卡审批隐藏
function getBack(node) {
    $("#cards").css("display","none");
}

// 领卡审批提交
function approval(node) {
    var path = $("#path").val();
    var cardPr = $("#cardPr").val();
    var gcNum = $("#gcNum").val();

    $.ajax({
        url:path+"/getCardServlet?methodName=cardExamine",
        type:"post",
        data:"cardPr="+cardPr+"&gcNum="+gcNum+"&gcId="+gcId+"&id="+Id+"",
        dataType:"text",
        beforeSend:function(){
            if (cardPr != null && cardPr != " " && cardPr < 1){
                alert("前缀不能为空");
                return false;
            }else {
                var a = confirm("是否确认通过审核");
                if (a){
                    return true;
                }else {
                    return false;
                }
            }
        },
        success:function (data) {
            if (data == "审核完成"){
                alert("审核完成")
            }
            if (data == "审核失败"){
                alert("审核失败")
            }
            if (data == "库存数量不足"){
                alert("库存数量不足")
            }


        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })

}
//售卡
function sellCard(node) {
    var past = $("#path").val();
    // 姓名
    var name = $("#name").val();
    // 年龄
    var age = $("#age").val();
    // 性别
    // var sex = $("#sex").val();
    var sex = $("input[name='sex']:checked").val();
    // 籍贯
    var birthplace = $("#birthplace").val();
    // 证件号
    var idNumber = $("#idNumber").val();
    // 电话
    var telephone = $("#telephone").val();
    // 住址
    var address = $("#address").val();
    // 预存金额
    var saveMoney = $("#saveMoney").val();
    // 卡号
    var cardNumber = $("#cardNumber").val();
    alert(name + "==" + age + "==" + sex + "==" + birthplace + "==" + idNumber + "==" + telephone + "==" + address + "==" + saveMoney + "==" + cardNumber);
    $.ajax({
        url:past+"/sellCardServlet?methodName=sell",
        type:"post",
        data:"name="+name+"&age="+age+"&sex="+sex+"&birthplace="+birthplace+"&idNumber="+idNumber+"&telephone="+telephone+"&address="+address+"&saveMoney="+saveMoney+"&cardNumber="+cardNumber,
        dataType:"text",
        beforeSend:function(){
            if (age.length <= 3 && age != null){

            }else {
                alert("年龄输入错误")
            }
            if (/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idNumber)){

            }else {
                alert("请输入正确的证件号");
                $("#sub").prop("disabled",true);
            }
            if(/^1[3578]\d{9}$/.test(telephone)){

            }else{
                alert("请输入正确的手机号");
                $("#sub").prop("disabled",true);
            }
            if (/^[0-9]*$/.test(saveMoney) && saveMoney >5){

            }else {
                alert("金额不能小于5元，且只能为整数");
                $("#sub").prop("disabled",false);
            }

        },
        success:function (data) {
            alert(data);
            if (data=="领卡成功"){

            }
        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })

}

//读卡
function ready() {
    alert("进来了")
    var past = $("#path").val();
    var read = $("#read").val();
    $.ajax({
        url:past+"/sellCardServlet?methodName=readCard",
        type:"post",
        data:"read="+read,
        dataType:"text",
        beforeSend:function(){

        },
        success:function (data) {
            var patients = JSON.parse(data);
            for(var i in patients){
                $("#name").val(patients[i].patName);
                $("#sex").val(patients[i].patSex);
                $("#age").val(patients[i].patAge);
                $("#np").val(patients[i].np);
                $("#number").val(patients[i].patStatus);
                $("#phone").val(patients[i].phoneNum);
                $("#address").val(patients[i].nowAdd);
                $("#balance").val(patients[i].money);
            }
        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })
}

// 退卡
function quitCard() {
    var past = $("#path").val();
    var read = $("#read").val();
    $.ajax({
        url:past+"/sellCardServlet?methodName=quitcard",
        type:"post",
        data:"read="+read,
        dataType:"text",
        beforeSend:function(){
            if (read == null && read.length < 1){
                alert("请先读卡");
                return false;
            }
        },
        success:function (data) {
            if (data == "退卡成功"){
                alert("退卡成功");
            }else if (data == "退卡失败"){
                alert("退卡失败");
            }

        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })
}


// 换卡
function changeCard() {
    var past = $("#path").val();
    var read = $("#read").val();
    var cardNum = $("#cardNum").val();
    $.ajax({
        url:past+"/sellCardServlet?methodName=changecard",
        type:"post",
        data:"read="+read+"&cardNum"+cardNum,
        dataType:"text",
        beforeSend:function(){
            if (read == null && read.length < 1){
                alert("请先读卡");
                return false;
            }
        },
        success:function (data) {
            if (data == "换卡成功"){
                alert("换卡成功");
            }else if (data == "换卡失败"){
                alert("换卡失败");
            }

        },
        error:function () {
            alert("网络繁忙");
        },
        complete:function () {

        }
    })

}