<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];
$oldPwd = $_POST['oldPwd'];
$newPwd = $_POST['newPwd'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select user_pwd from user where user_id = '{$userId}';";
$records = mysqli_query($db, $query_sql);
$row = mysqli_fetch_row($records);

// 逻辑处理
// code 0 修改成功
// code 1 旧密码错误
// code 2 修改密码失败
if($row[0] != $oldPwd){
    $resCode = "1";
}else{
    $update_sql = "update user set user_pwd = '{$newPwd}' where user_id = '{$userId}';";
    $ifUpdate = mysqli_query($db, $update_sql);
    if($ifUpdate){
        $resCode = "0";
    }else{
        $resCode = "2";
    }
}

// 返回值
$resStr = array("code" => $resCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

