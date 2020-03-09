<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminId = $_POST['adminId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from teacher where teacher_id = '{$adminId}';";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
$row = mysqli_fetch_row($record);
$adminId = $row[0];
$adminName = $row[1];
$adminPwd = $row[2];
$adminClass = $row[3];
$adminPhone = $row[4];

// 返回值
$resStr = array("adminId"=>$adminId, "adminName"=>$adminName, "adminPwd"=>$adminPwd, "adminClass"=>$adminClass,
                "adminPhone"=>$adminPhone);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);
