document.write("<script language=javascript src='../js/jQuery.js'></script>");

function userLeaveView() {
    var userId = localStorage.getItem('userId');

    $.ajax({
        type: "POST",
        url: "../function/userLeaveView.php",
        data: {userId:userId},
        dataType: "text",
        success: function(data){
            // alert(data);
            document.getElementById("user_leave_info").value = data;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}