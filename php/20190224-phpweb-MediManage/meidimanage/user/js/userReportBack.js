document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userGetLeaveInfo(){
    var userId = localStorage.getItem('userId');
    $.ajax({
        type: "POST",
        url: "../function/userGetLeaveInfo.php",
        data: {userId:userId},
        dataType: "json",
        success: function(data){
            // data.code 1败 0成
            // data.infoId 请假信息的id
            // data.infoData 请假信息
            // alert(data);
            if(data.code == 1){
                alert("没有要销假的信息！");
                window.location.href = "mainPage.html";
            }else{
                document.getElementById("info_id").value = data.infoId;
                document.getElementById("user_leave_info").value = data.infoData;
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function userReportBack() {
    var infoId = document.getElementById("info_id").value;

    $.ajax({
        type: "POST",
        url: "../function/userReportBack.php",
        data: {infoId:infoId},
        dataType: "json",
        success: function(data){
             // alert(data);
             if (data.code == 0){
                 alert("销假记录已经上传！请等待审核！");
             }else{
                 alert("销假失败！请刷新重试！");
             }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}