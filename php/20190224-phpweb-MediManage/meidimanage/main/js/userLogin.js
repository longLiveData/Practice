document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userLogin() {
    var userId = document.getElementById("user_id").value;
    if(userId == ""){
        alert("请输入用户名！");
        return;
    }
    var userPwd = document.getElementById("user_pwd").value;
    if(userPwd == ""){
        alert("请输入用户名！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/userLogin.php",
        data: {userId:userId, userPwd:userPwd},
        dataType: "json",
        success: function(data){
            if(data.code == 2){
                alert("用户不存在！请先注册！");
                window.location.href="userEnroll.html";
            }else if(data.code == 1){
                alert("用户名或密码错误！");
            }
            else if(data.code == 0){
                alert("欢迎您！" + name);
                localStorage.setItem('userId',userId);
                window.location.href = "../../user/page/mainPage.html";
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}