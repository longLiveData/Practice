<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from leaveInfo where info_stu_id = '{$userId}' and info_state = '1';";
$record = mysqli_query($db, $query_sql);


// 逻辑处理
if(mysqli_num_rows($record) == 0){
    $vCode = "1";
    $resStr = array("code" => $vCode);
}else{
    $vCode = "0";
    $row = mysqli_fetch_row($record);
    $vInfoId = $row[0];
    $infoStuId = $row[1];
    $infoStuName = $row[3];
    $infoStuClass = $row[2];
    $infoContent = $row[4];
    $infoLeaveTime = $row[5];
    $infoBackTime = $row[6];
    $infoStateCode = $row[7];
    if ($infoStateCode == 0) {
        $infoState = "已提交,教师审核中";
    } else if ($infoStateCode == 1) {
        $infoState = "请假中";
    } else if ($infoStateCode == 2) {
        $infoState = "拒绝请假";
    } else if ($infoStateCode == 3) {
        $infoState = "已返回,销假审核中";
    } else if ($infoStateCode == 4) {
        $infoState = "已销假";
    }
    $infoLeaveComtime = $row[8];
    $infoBackComtimeStr = $row[9];
    if ($infoBackComtimeStr == null) {
        $infoBackComtime = "没有记录";
    } else {
        $infoBackComtime = $infoBackComtimeStr;
    }
    $infoLeaveVeritimeStr = $row[10];
    if ($infoLeaveVeritimeStr == null) {
        $infoLeaveVeritime = "没有记录";
    } else {
        $infoLeaveVeritime = $infoLeaveVeritimeStr;
    }
    $infoBackVeritimeStr = $row[11];
    if ($infoBackVeritimeStr == null) {
        $infoBackVeritime = "没有记录";
    } else {
        $infoBackVeritime = $infoBackVeritimeStr;
    }
    $infoMessageStr = $row[12];
    if ($infoMessageStr == null) {
        $infoMessage = "没有记录";
    } else {
        $infoMessage = $infoMessageStr;
    }
    $vInfoData = "请假记录：" .
        "\r\n学号：" . $infoStuId.
        "\r\n姓名：" . $infoStuName.
        "\r\n班级：" . $infoStuClass.
        "\r\n假条内容：" . $infoContent.
        "\r\n请求离校时间：" . $infoLeaveTime.
        "\r\n请求返校时间：" . $infoBackTime.
        "\r\n假条状态：" . $infoState.
        "\r\n请假申请时间：" . $infoLeaveComtime.
        "\r\n假条审核时间：" . $infoLeaveVeritime.
        "\r\n销假申请时间：" . $infoBackComtime.
        "\r\n销假审核时间：" . $infoBackVeritime.
        "\r\n教师留言：" . $infoMessage.
        "\r\n";

    $resStr = array("code" => $vCode, "infoId" => $vInfoId, "infoData" => $vInfoData);
}

// 返回值
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

