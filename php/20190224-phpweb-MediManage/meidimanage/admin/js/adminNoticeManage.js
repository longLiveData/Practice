document.write("<script language=javascript src='../js/jQuery.js'></script>");

function showNotice() {
    $.ajax({
        type: "POST",
        url: "../function/getNotice.php",
        data: {},
        dataType: "json",
        success: function(data){
            // alert(data);
            var divData = "";
            for (var i = 0; i < data.num; i++){
                var rec = data[i][i];
                var recStr = JSON.stringify(rec);
                divData += "<div>";
                divData += "<a class='noticeTopic'>"+rec.noticeTopic+"</a><br>";
                divData += "<a class='noticeInfo'>发布者："+rec.noticePerson+"</a><br>";
                divData += "<a class='noticeInfo'>最后修改时间："+rec.noticeTime+"</a><br>";
                divData += "<a class='noticeContent'>"+rec.noticeContent+"</a><br>";
                divData += "<input class='smallBtn' type='button' value='修改' onclick='adminNoticeModify("+recStr+")'>";
                divData += "<input class='smallBtn' type='button' value='删除' onclick='adminNoticeDelete("+rec.noticeId+")'>";
                divData += "</div><br><br>";
            }
            document.getElementById("noticeShow").innerHTML = divData;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function adminNoticeModify(rec) {
    //rec = JSON.parse(rec);
    var divData = "";
    divData += "<div  style=\"position: relative;left:430px;top:100px; width:740px;height:730px;border:1px solid black;\n" +
        "                border-radius: 30px;\">";
    divData += "<label class=\"showLabel\" style=\"left:20px;top:40px;\">修改通知：</label><br><br>";
    divData += "<label class=\"showLabel\" style=\"left:20px;top:40px;\">发布者姓名：</label>";
    divData += "<input class=\"textInput\" style=\"left:20px;top:40px;font-size:18px;\" " +
        "type='text' id='noticeNameModify'><br><br>";
    divData += "<label class=\"showLabel\" style=\"left:20px;top:40px;\">标题：</label>";
    divData += "<input class=\"textInput\" style=\"left:20px;top:40px;font-size:18px;\" " +
        "type='text' id='noticeTopicModify'><br><br><br><br>";
    divData += "<label class=\"showLabel\" style=\"left:20px;top:-430px;\">正文：</label>";
    divData += "<textarea class='textArea' id='noticeContentModify'" +
                "style=\"left:30px;width:560px;height:400px;padding: 30px;\"></textarea><br><br>";
    divData += `<input class='smallBtn' type='button' value='提交修改' style="left:300px;" 
                   onclick='adminNoticeModifySubmit(${rec.noticeId})'>`;
    document.getElementById("modifyNoticeShow").innerHTML = divData;

    document.getElementById("noticeNameModify").value = rec.noticePerson;
    document.getElementById("noticeTopicModify").value = rec.noticeTopic;
    document.getElementById("noticeContentModify").value = rec.noticeContent;
}

function adminNoticeModifySubmit(noticeId){
    var noticeName = document.getElementById("noticeNameModify").value;
    var noticeTopic = document.getElementById("noticeTopicModify").value;
    var noticeContent = document.getElementById("noticeContentModify").value;
    $.ajax({
        type: "POST",
        url: "../function/adminNoticeModify.php",
        data: {noticeId:noticeId, noticeName:noticeName, noticeTopic:noticeTopic, noticeContent:noticeContent},
        dataType: "json",
        success: function(data){
            // alert(data);
            if(data.code=="0"){
                alert("修改成功！");
                window.location.href="adminNoticeManage.html";
            }else{
                alert("修改失败！请刷新重试！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function adminNoticeDelete(noticeId) {
    $.ajax({
        type: "POST",
        url: "../function/adminNoticeDelete.php",
        data: {noticeId:noticeId},
        dataType: "json",
        success: function(data){
            // alert(data);
            if(data.code=="0"){
                alert("删除成功！");
                window.location.href="adminNoticeManage.html";
            }else{
                alert("删除失败！请刷新重试！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}