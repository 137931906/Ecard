onload = function () {


    var account = byId();
    console.log(account);
    var account_jq = $("#account");
    console.log(account_jq);
};
    function createTable(buttonNode) {
  var row = document.getElementById("row").value;
     var col = document.getElementById("col").value;

        var html=``;
       var html2=``;
       for (var i = 0;i<row;i++){
           html2=``;
           for (var j = 0;j<col;j++){
               if (j==col-1){
                   html2+=`
                   <td><button onclick="deleteTd(this)">删除</button></td>
                  `
               }else{
                   html2+=`
                   <td>"第"`+i+`"行,第"`+j+`"列"</td>
                  `
               }
           }
           html+=`
               <tr>
               `+html2+`
               </tr>
           `
       }

        table2.innerHTML=html



}
    function deleteTd(node) {
        //父级的父级 整个tr移除
        node.parentNode.parentNode.remove();
        if (table2.childNodes[1].childElementCount==0){
            table2.remove();
        }
    }

    var account = byId();
    console.log(account);
    var account_jq = $("#account");
    console.log(account_jq);

    function  byId() {

    }

    $(function () {




            var $btn = $("#btn");
            console.log($btn);

            $btn.on("mouseover", function () {
                $btn.css("width", "200px").css("height", "100px")
                    .css("backgroundColor", "yellow")
            }).on("mouseout", function () {
                $btn.css("width", "100").css("height", "50")
                    .css("backgroundColor", "pink")
            });


            // console.log($("#user_tab tr:gt(0):odd"));
            $("#user_tab tr:gt(0):odd").css("backgroundColor", "pink");
            $("#user_tab tr:gt(0):even").css("backgroundColor", "yellow");
    })

function js_ajax() {
    // alert("进来没有");
    //获取到路径
    var path = $("#path").val();
    var account = $("#account").val();
    var password = $("#password").val();
    var vCode = $("#vCode").val();

    //使用json
    var admin =  {"account":account,"password":password};
    var adminJson = JSON.stringify(admin);
    var xhr = new XMLHttpRequest();
    if (vCode==""&&vCode.length==0){
        alert("请输入验证码");

    }else {
        xhr.open("post",path+"l");
        xhr.send(adminJson+"&"+vCode);
        //get 可要可不要  post 需要send
        //字符串拼接
        // xhr.send(account+"&"+password);
        //当状态发生改变就触发
        xhr.onreadystatechange =function () {
            //状态码为4时     status 200正确返回

            if (xhr.readyState==4 && xhr.status ==200){
                alert(xhr.responseText)
                if (xhr.responseText=="登录成功") {
                    location.href=path+"menuServlet";
                }else{
                    changeImg();
                }

            }

        }
    }



}

/**
 * 修改验证码图片
 */
function changeImg() {
    alert("hhh")
    var path = $("#path").val();
    var date = new Date();


    //刷新验证码
    var img = document.getElementById('vImg');
    img.src = path+"verifyCodeServlet?date="+date;

}



