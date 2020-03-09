document.write("<script language=javascript src='../js/jQuery.js'></script>");

function getStudentInfo() {
    var adminClass = localStorage.getItem('adminClass');

    $.ajax({
        type: "POST",
        url: "../function/getStudentInfo.php",
        data: {adminClass: adminClass},
        dataType: "json",
        success: function(data){
            var tableData = "";
            for (var i = 0; i < data.num; i++){
                var rec = data[i][i];
                tableData += "<tr>";
                tableData += "<td>"+rec.stuId+"</td>";
                tableData += "<td>"+rec.stuName+"</td>";
                var state = "";
                if(rec.stuState == 0){
                    state = "正常"
                }else if(rec.stuState == 1){
                    state = "请假";
                }else{
                    state = "旷课";
                }
                tableData += "<td>"+state+"</td>";
                tableData += "<td>"+rec.stuGName+"</td>";
                tableData += "<td>"+rec.stuGPhone+"</td>";
                tableData += "<td><input type='button' class='smallBtn' value='查看' onclick='showLeaveInfo("+ rec.stuId +")'></td>";
                tableData += "</tr>";
            }
            document.getElementById("stuInfo").innerHTML = tableData;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
    });
}

function showLeaveInfo(userId) {
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