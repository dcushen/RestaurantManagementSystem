<?php
    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $shiftId = $_POST["shiftId"];
    $employeeId = $_POST["name"];
    $startTime = $_POST["start"];
    $endTime = $_POST["end"];
   

    $statement = mysqli_prepare($con, "INSERT INTO roster (shiftId, employeeId, startTime, endTime) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $shiftId, $employeeId, $startTime, $endTime);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>