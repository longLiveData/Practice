document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userModifyPwd() {
    var oldPwd = document.getElementById("old_pwd").value;
    var newPwd = document.getElementById("new_pwd").value;
    var newPwd2 = document.getElementById("new_pwd2").value;
    var userId = localStorage.getItem('userId');

    if(oldPwd == ""){
        alert("请输入旧密码！");
        return;
    }
    if(newPwd == ""){
        alert("请输入新密码！");
        return;
    }
    if(newPwd2 == ""){
        alert("请确认新密码！");
        return;
    }if(newPwd2 != newPwd){
        alert("两次密码输入不一致！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/userModifyPwd.php",
        data: {userId:userId, oldPwd:oldPwd, newPwd:newPwd},
        dataType: "json",
        success: function(data){
            // code 0 修改成功
            // code 1 旧密码错误
            // code 2 修改密码失败
            if(data.code == 0){
                alert("密码修改成功！返回主页！");
                window.location.href="mainPage.html";
            }else if(data.code == 1){
                alert("旧密码错误！");
            }else if(data.code == 2){
                alert("密码修改失败！请刷新重试！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}