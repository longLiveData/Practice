<?php
header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$infoId = $_POST['infoId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$update_sql = "update leaveInfo set info_state = '3', info_back_comtime = current_timestamp where ".
            "info_id = {$infoId};";
$ifSuccess = mysqli_query($db, $update_sql);

// 逻辑处理
if ($ifSuccess){
    $vCode = "0";
}else{
    $vCode = "1";
}

// 返回值
$resStr = array("code" => $vCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

