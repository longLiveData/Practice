document.write("<script language=javascript src='../js/jQuery.js'></script>");

function noticeSubmit() {
    var noticeName = document.getElementById("notice_name").value;
    var noticeTopic = document.getElementById("notice_topic").value;
    var noticeContent = document.getElementById("notice_content").value;
    if(noticeName == ""){
        alert("请输入发布者姓名！");
        return;
    }
    if(noticeTopic == ""){
        alert("请输入标题");
        return;
    }
    if(noticeContent == ""){
        alert("请输入正文！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "../function/adminNoticeSubmit.php",
        data: {noticeName: noticeName, noticeTopic: noticeTopic, noticeContent: noticeContent},
        dataType: "json",
        success: function(data){
            // alert(data);
            if(data.code == 1){
                alert("发布失败！请刷新重试！");
            }else if(data.code == 0){
                alert("发布成功！");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

