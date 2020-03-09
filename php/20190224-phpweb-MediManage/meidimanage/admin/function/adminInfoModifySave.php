<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminId = $_POST['adminId'];
$adminName = $_POST['adminName'];
$adminPwd = $_POST['adminPwd'];
$adminClass = $_POST['adminClass'];
$adminPhone = $_POST['adminPhone'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$update_sql = "update teacher set teacher_name = '{$adminName}', teacher_pwd = '{$adminPwd}',"." teacher_class='".
            "{$adminClass}',teacher_phone = '{$adminPhone}' where teacher_id = '{$adminId}';";
$ifSuccess = mysqli_query($db, $update_sql);

// 逻辑处理
if($ifSuccess){
    $vCode = "0";
}else{
    $vCode = "1";
}

// 返回值
$resStr = array("code"=>$vCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);