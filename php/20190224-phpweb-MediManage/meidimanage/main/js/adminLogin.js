document.write("<script language=javascript src='../js/jQuery.js'></script>");

function adminLogin() {
    var adminId = document.getElementById("admin_id").value;
    if(adminId == ""){
        alert("请输入用户名！");
        return;
    }
    var adminPwd = document.getElementById("admin_pwd").value;
    if(adminPwd == ""){
        alert("请输入密码！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/adminLogin.php",
        data: {adminId:adminId, adminPwd:adminPwd},
        dataType: "json",
        success: function(data){
            // code 2 用户不存在
            // code 1 用户名或密码错误
            // code 0 登陆成功
            // alert(data);
            if(data.code == 2){
                alert("该教师不存在！");
            }else if(data.code == 1){
                alert("用户名或密码错误！");
            }
            else if(data.code == 0){
                alert("登录成功！");
                localStorage.setItem("adminId", adminId);
                window.location.href = "../../admin/page/mainPage.html";;
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}