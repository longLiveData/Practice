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
                divData += "<div>";
                divData += "<a class='noticeTopic'>"+rec.noticeTopic+"</a><br>";
                divData += "<a class='noticeInfo'>发布者："+rec.noticePerson+"</a><br>";
                divData += "<a class='noticeInfo'>发布时间："+rec.noticeTime+"</a><br>";
                divData += "<a class='noticeContent'>"+rec.noticeContent+"</a><br>";
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