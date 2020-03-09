<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$infoStuId = $_POST['infoStuId'];
$infoStuClass = $_POST['infoStuClass'];
$infoStuName = $_POST['infoStuName'];
$infoContent = $_POST['infoContent'];
$infoLeaveTime = $_POST['infoLeaveTime'];
$infoBackTime = $_POST['infoBackTime'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$insert_sql = "insert into leaveInfo(info_stu_id, info_stu_class, info_stu_name, info_content, info_leave_time, ".
            "info_back_time,info_state, info_leave_comtime) value('{$infoStuId}','{$infoStuClass}','{$infoStuName}',".
            "'{$infoContent}','{$infoLeaveTime}','{$infoBackTime}','0',current_timestamp);";
$ifSuccess = mysqli_query($db, $insert_sql);

// 逻辑处理
// code 0 提交成功
// code 1 提交失败
if($ifSuccess){
    $resCode = "0";
}else{
    $resCode = "1";
}

// 返回值
$resStr = array("code" => $resCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

