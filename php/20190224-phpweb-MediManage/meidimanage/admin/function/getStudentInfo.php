<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据
$adminClass = $_POST['adminClass'];

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from students where stu_class = '{$adminClass}';";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
$resStr = array("num"=>mysqli_num_rows($record));
$count = 0;
while($row = mysqli_fetch_row($record)){
    $stuId = $row[0];
    $stuName = $row[1];
    $stuState = $row[4];
    $stuGName = $row[6];
    $stuGPhone = $row[7];
    $rec = array("stuId"=>$stuId, "stuName"=>$stuName, "stuState"=>$stuState, "stuGName"=>$stuGName, "stuGPhone"=>$stuGPhone);
    $recStr = array($count=>$rec);
    array_push($resStr,$recStr);
    $count += 1;
}

// 返回值
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);