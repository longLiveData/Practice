document.write("<script language=javascript src='../js/jQuery.js'></script>");

function getAdminInfo() {
    var adminId = localStorage.getItem('adminId');

    $.ajax({
        type: "POST",
        url: "../function/getAdminInfo.php",
        data: {adminId: adminId},
        dataType: "json",
        success: function(data){
            // alert(data);
            document.getElementById("admin_id").value = data.adminId;
            document.getElementById("admin_name").value = data.adminName;
            document.getElementById("admin_pwd").value = data.adminPwd;
            document.getElementById("admin_pwd2").value = data.adminPwd;
            document.getElementById("admin_class").value = data.adminClass;
            document.getElementById("admin_phone").value = data.adminPhone;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function adminInfoModifySave() {
    var adminId = localStorage.getItem('adminId');
    var adminName = document.getElementById('admin_name').value;
    if(adminName == ""){
        alert("请输入用户名字！");
        return;
    }
    var adminPwd = document.getElementById('admin_pwd').value;
    if(adminPwd == ""){
        alert("请输入密码！");
        return;
    }
    var adminPwd2 = document.getElementById('admin_pwd2').value;
    if(adminPwd2 == ""){
        alert("请输入确认密码！");
        return;
    }
    if (adminPwd != adminPwd2){
        alert("两次密码不一致！");
        return;
    }
    var adminClass = document.getElementById('admin_class').value;
    if(adminClass == ""){
        alert("请输入班级！");
        return;
    }
    var adminPhone = document.getElementById('admin_phone').value;
    if(adminPhone == ""){
        alert("请输入电话号码！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/adminInfoModifySave.php",
        data: {adminId: adminId, adminName:adminName, adminPwd:adminPwd, adminClass:adminClass,
                adminPhone:adminPhone},
        dataType: "json",
        success: function(data){
            // code 0 修改成功
            // code 1 修改失败
            // alert(data);
            if(data.code == "0"){
                alert("修改成功！");
            }else{
                alert("修改失败！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}