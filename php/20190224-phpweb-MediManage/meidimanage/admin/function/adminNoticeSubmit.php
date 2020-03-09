<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$noticeName = $_POST['noticeName'];
$noticeTopic = $_POST['noticeTopic'];
$noticeContent = $_POST['noticeContent'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$insert_sql = "insert into notice(notice_person, notice_time, notice_topic, notice_content) value".
                "('{$noticeName}', current_timestamp, '{$noticeTopic}', '{$noticeContent}');";
$ifSuccess = mysqli_query($db, $insert_sql);

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