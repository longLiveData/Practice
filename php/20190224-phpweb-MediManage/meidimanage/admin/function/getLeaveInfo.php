<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminClass = $_POST['adminClass'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from leaveInfo where info_state = '0' and info_stu_class = '{$adminClass}';";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
$num = mysqli_num_rows($record);
if($num == 0){
    $vCode = "1";
    $resStr = array("code" => $vCode);
}else{
    $vCode = "0";

    $row = mysqli_fetch_row($record);
    $infoId = $row[0];
    $infoStuId = $row[1];
    $infoStuClass = $row[2];
    $infoStuName = $row[3];
    $infoContent = $row[4];
    $infoLeaveTime = $row[5];
    $infoBackTime = $row[6];
    $infoLeaveComtime = $row[8];
    $infoNums = $num ;

    $resStr = array("code"=>$vCode, "infoId"=>$infoId, "infoStuId"=>$infoStuId, "infoStuClass"=>$infoStuClass,
        "infoStuName"=>$infoStuName, "infoContent"=>$infoContent, "infoLeaveTime"=>$infoLeaveTime,
        "infoBackTime"=>$infoBackTime, "infoLeaveComtime"=>$infoLeaveComtime, "infoNum"=>$infoNums);
}

// 返回值
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);