<?php
$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$fullName = $_GET['name'];
$date = $_GET['date'];
$time = $_GET['time'];
$tableNo = $_GET['table'];
$phone = $_GET['phone'];
$bookID = $_GET['bookID'];

$result = mysqli_query($con,"INSERT INTO reservations(name, date, time, tableNo, phone, bookID)
          VALUES ('$fullName', '$date', '$time', '$tableNo', '$phone', '$bookID')");

if($result == true) {
    echo '{"query_result":"SUCCESS"}';
}
else{
    echo '{"query_result":"FAILURE"}';
}
mysqli_close($con);
?>