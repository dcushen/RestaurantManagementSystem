<?php
$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$number = $_GET['number'];
$time = $_GET['time'];



$result = mysqli_query($con,"INSERT INTO clock(empNum, clockIn)
          VALUES ('$number', '$time')");

if($result == true) {
    echo '{"query_result":"SUCCESS"}';
}
else{
    echo '{"query_result":"FAILURE"}';
}
mysqli_close($con);
?>