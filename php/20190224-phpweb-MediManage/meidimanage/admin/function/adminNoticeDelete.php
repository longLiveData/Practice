<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$noticeId = $_POST['noticeId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$delete_sql = "delete from notice where notice_id = '{$noticeId}';";
$ifSuccess = mysqli_query($db, $delete_sql);

// 逻辑处理
if($ifSuccess){
    $resStr = array("code"=>0);
}else{
    $resStr = array("code"=>1);
}

// 返回值
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);