<?php
header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from leaveInfo where info_stu_id = '{$userId}';";
$records = mysqli_query($db, $query_sql);

// 逻辑处理
if(mysqli_num_rows($records) == 0){
    $resStr = "没有请假记录!：";
}else{
    $resStr = "请假记录：";
    // 组装请假记录
    $rows = mysqli_fetch_all($records);
    for ($i = 0;$i < count($rows); $i++) {
        $row = $rows[$i];
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

        $resStr = $resStr .
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
    }
}

// 返回值
exit($resStr);

