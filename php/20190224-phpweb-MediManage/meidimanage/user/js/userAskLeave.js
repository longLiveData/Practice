document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userAskLeave() {
    var infoStuId = document.getElementById("info_stu_id").value;
    var infoStuClass = document.getElementById("info_stu_class").value;
    var infoStuName = document.getElementById("info_stu_name").value;
    var infoContent = document.getElementById("info_content").value;
    var infoLeaveTime = document.getElementById("info_leave_time").value;
    var infoBackTime = document.getElementById("info_back_time").value;

    if(infoContent == ""){
        alert("请输入假条内容！");
        return;
    }
    if(infoLeaveTime == ""){
        alert("请输入离校时间！");
        return;
    }
    if(infoBackTime == ""){
        alert("请确认返校时间！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/userAskLeave.php",
        data: {infoStuId:infoStuId, infoStuClass:infoStuClass, infoStuName:infoStuName,infoContent:infoContent,
            infoLeaveTime:infoLeaveTime, infoBackTime:infoBackTime},
        dataType: "json",
        success: function(data){
            // code 0 提交成功
            // code 1 提交失败
            // alert(data);
            if(data.code == 0){
                alert("请假申请成功！请等待审核！");
            }else if(data.code == 1){
                alert("请假申请失败！请刷新重试！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function getStuInfo() {
    userId = localStorage.getItem("userId");
    document.getElementById("info_stu_id").value = userId;

    $.ajax({
        type: "POST",
        url: "../function/getStuInfo.php",
        data: {userId:userId},
        dataType: "json",
        success: function(data){
            // data.stuClass 班级
            // data.stuName 姓名
            // alert(data);
            document.getElementById("info_stu_name").value = data.stuName;
            document.getElementById("info_stu_class").value = data.stuClass;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });

}