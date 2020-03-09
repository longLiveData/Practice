<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$userId = $_POST['userId'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from students where stu_id = '{$userId}';";
$record = mysqli_query($db, $query_sql);
$row = mysqli_fetch_row($record);

// 逻辑处理
$stuId  = $row[0];
$stuName = $row[1];
$stuClass = $row[2];
$stuHistory = $row[3];
if($row[4] == 0){
    $stuState = "在校";
}else if($row[4] == 1){
    $stuState = "请假中";
}else if($row[4] == 2){
    $stuState = "旷课";
}
$stuTeacherId = $row[5];
$stuGuardianName = $row[6];
$stuGuardianPhone = $row[7];

$teacher_query_sql = "select teacher_name, teacher_phone from teacher where teacher_id = '{$stuTeacherId}';";
$teacher_record = mysqli_query($db, $teacher_query_sql);
$teacher_row = mysqli_fetch_row($teacher_record);
$stuTeacherName = $teacher_row[0];
$stuTeacherPhone = $teacher_row[1];

$resStr = "基本信息：".
    "\r\n学号：". $stuId.
    "\r\n姓名：".$stuName.
    "\r\n班级：".$stuClass.
    "\r\n病史：".$stuHistory.
    "\r\n在校状态：".$stuState.
    "\r\n班主任姓名：".$stuTeacherName.
    "\r\n班主任电话：".$stuTeacherPhone.
    "\r\n监护人姓名：".$stuGuardianName.
    "\r\n监护人联系方式：".$stuGuardianPhone;

// 返回值
exit($resStr);

