<?php

header('Content-Type:application/json; charset=utf-8');

// 解析前台数据

// 数据库操作
$db = new mysqli('localhost', 'root', '1234');
mysqli_select_db($db,'medimanage');
$query_sql = "select * from notice order by notice_time desc;";
$record = mysqli_query($db, $query_sql);

// 逻辑处理
$resStr = array("num"=>mysqli_num_rows($record));
$count = 0;
while($row = mysqli_fetch_row($record)){
    $noticeTime = $row[2];
    $noticePerson = $row[1];
    $noticeTopic = $row[3];
    $noticeContent = $row[4];
    $rec = array("noticeTime"=>$noticeTime, "noticePerson"=>$noticePerson, "noticeTopic"=>$noticeTopic,
                "noticeContent"=>$noticeContent);
    $recStr = array($count=>$rec);
    array_push($resStr,$recStr);
    $count += 1;
}


// 返回值
$resJson = json_encode($resStr, JSON_UNESCAPED_UNICODE);
exit($resJson);

