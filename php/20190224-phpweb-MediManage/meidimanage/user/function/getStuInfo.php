<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select stu_name, stu_class from students where stu_id = '{$userId}';";
$record = mysqli_query($db, $query_sql);
$row = mysqli_fetch_row($record);

// 逻辑处理
$stuName = $row[0];
$stuClass = $row[1];

// 返回值
$resStr = array("stuName" => $stuName, "stuClass" => $stuClass);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

