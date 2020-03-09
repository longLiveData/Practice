<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];
$userPwd = $_POST['userPwd'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select user_pwd from user where user_id = '{$userId}'";
$records = mysqli_query($db, $query_sql);

// 逻辑处理
if(mysqli_num_rows($records) == 0){
    $resCode = "2";
}else{
    $row = mysqli_fetch_row($records);
    if($row[0] != $userPwd){
        $resCode = "1";
    }else{
        $resCode = "0";
    }
}

// 返回值
$resStr = array("code" => $resCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

