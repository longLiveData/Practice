<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminId = $_POST['adminId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select teacher_class from teacher where teacher_id = '{$adminId}';";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
$row = mysqli_fetch_row($record);
$stuClass = $row[0];

// 返回值
exit($stuClass);
