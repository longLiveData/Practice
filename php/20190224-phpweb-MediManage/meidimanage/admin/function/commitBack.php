<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$infoId = $_POST['infoId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select info_state from leaveInfo where info_id = '{$infoId}';";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
// code 0 审核提交成功
// code 1 已经审核
// code 2 提交失败
if (mysqli_fetch_row($record)[0] != "3"){
    $vCode = "1";
}else{
    $update_sql = "update leaveInfo set info_state = '4', info_back_veritime = current_timestamp ".
        " where info_id = '{$infoId}'";
    $ifSuccess = mysqli_query($db, $update_sql);
    if($ifSuccess){
        $vCode = "0";
    }else{
        $vCode = "2";
    }
}

// 返回值
$resStr = array("code"=>$vCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);