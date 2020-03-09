<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminId = $_POST['adminId'];
$adminPwd = $_POST['adminPwd'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select teacher_pwd from teacher where teacher_id = '{$adminId}'";
$records = mysqli_query($db, $query_sql);

// 逻辑处理
// code 2 用户不存在
// code 1 用户名或密码错误
// code 0 登陆成功
if(mysqli_num_rows($records) == 0){
    $resCode = "2";
}else{
    $row = mysqli_fetch_row($records);
    if($row[0] == $adminPwd){
        $resCode = "0";
    }else{
        $resCode = "1";
    }
}

// 返回值
$resStr = array("code" => $resCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

