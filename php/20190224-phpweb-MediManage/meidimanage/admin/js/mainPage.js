document.write("<script language=javascript src='../js/jQuery.js'></script>");

function getAdminClass() {
    var adminId = localStorage.getItem('adminId');

    $.ajax({
        type: "POST",
        url: "../function/getAdminClass.php",
        data: {adminId:adminId},
        dataType: "text",
        success: function(data){
            // alert(data);
            localStorage.setItem("adminClass", data);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}