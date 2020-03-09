document.write("<script language=javascript src='../js/jQuery.js'></script>");

function getBackInfo() {
    var adminClass = localStorage.getItem('adminClass');

    $.ajax({
        type: "POST",
        url: "../function/getBackInfo.php",
        data: {adminClass: adminClass},
        dataType: "json",
        success: function(data){
            // alert(data);
            if(data.code == 1){
                alert("没有申请要审核了！返回主页！");
                window.location.href="mainPage.html";
            }else{
                document.getElementById("info_id").value = data.infoId;
                document.getElementById("info_stu_id").value = data.infoStuId;
                document.getElementById("info_stu_class").value = data.infoStuClass;
                document.getElementById("info_stu_name").value = data.infoStuName;
                document.getElementById("info_content").value = data.infoContent;
                document.getElementById("info_leave_time").value = data.infoLeaveTime;
                document.getElementById("info_back_time").value = data.infoBackTime;
                document.getElementById("info_leave_comtime").value = data.infoLeaveComtime;
                document.getElementById("info_leave_veritime").value = data.infoLeaveVeritime;
                document.getElementById("info_back_comtime").value = data.infoBackComtime;
                var infoNum = data.infoNum;
                document.getElementById("mention").value = "还有"+infoNum+"条申请待处理！";
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function commitBack() {
    var infoId = document.getElementById("info_id").value;

    $.ajax({
        type: "POST",
        url: "../function/commitBack.php",
        data: {infoId: infoId},
        dataType: "json",
        success: function(data){
            // code 0 审核成功
            // code 1 已经审核
            // code 2 提交失败
            // alert(data);
            if(data.code == "0"){
                alert("提交审核成功！");
            }else if(data.code == "2"){
                alert("提交失败！请刷新重试！");
            }else{
                alert("已经审核过了！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}