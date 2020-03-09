document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userEnroll() {
    var userId = document.getElementById("user_id").value;
    var userName = document.getElementById("user_name").value;
    var userPwd = document.getElementById("user_pwd").value;
    var userPwd2 = document.getElementById("user_pwd2").value;

    if(userId == ""){
        alert("请输入学号！");
        return;
    }
    if(userName == ""){
        alert("请输入姓名！");
        return;
    }
    if(userPwd == ""){
        alert("请输入密码！");
        return;
    }
    if(userPwd2 == ""){
        alert("请输入确认密码！");
        return;
    }
    if(userPwd2 != userPwd){
        alert("两次密码不一致！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/userEnroll.php",
        data: {userId:userId, userName:userName, userPwd:userPwd},
        dataType: "json",
        success: function(data){
            // code 0 注册成功
            // code 1 学生不存在
            // code 2 学号和姓名不一致
            // code 3 已经注册过
            // code 4 插入表失败
            if(data.code == 0){
                alert("注册成功！请重新登录！");
                window.location.href="userLogin.html";
            }else if(data.code == 1){
                alert("没有该学生！");
            }else if(data.code == 2){
                alert("学生姓名和学号不一致！");
            }else if(data.code == 3){
                alert("该学生已经注册过！");
            }else if(data.code == 4){
                alert("创建用户失败！请刷新重试！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}