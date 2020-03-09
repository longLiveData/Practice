<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];
$userName = $_POST['userName'];
$userPwd = $_POST['userPwd'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_stu_sql = "select stu_name from students where stu_id = '{$userId}'";
$stu_records = mysqli_query($db, $query_stu_sql);
$query_user_sql = "select * from user where user_id = '{$userId}'";
$user_records = mysqli_query($db, $query_user_sql);

// 逻辑处理
// code 0 注册成功
// code 1 学生不存在
// code 2 学号和姓名不一致
// code 3 已经注册过
// code 4 插入表失败
if(mysqli_num_rows($stu_records) == 0){
    $resCode = "1";
}else{
    $stu_row = mysqli_fetch_row($stu_records);
    if($stu_row[0] != $userName){
        $resCode = "2";
    }else if(mysqli_num_rows($user_records) != 0){
        $resCode = "3";
    }else{
        $insert_sql = "insert into user value('{$userId}','{$userName}','{$userPwd}');";
        $ifInsert = mysqli_query($db, $insert_sql);
        echo $insert_sql;
        if($ifInsert){
            $resCode = "0";
        }
        else{
            $resCode = "5";
        }
    }
}

// 返回值
$resStr = array("code" => $resCode);
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

