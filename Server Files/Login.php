<?php
    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $employeeId = $_POST["employeeId"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM staff WHERE employeeId= ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $employeeId, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $employeeId, $password, $firstname, $surname, $job, $dob, $number, $level);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["employeeId"] = $employeeId;
        $response["password"] = $password;
        $response["firstname"] = $firstname;
        $response["surname"] = $surname;
        $response["job"] = $job;
        $response["dob"] = $dob;
        $response["number"] = $number;
        $response["level"] = $level;
    }
    
    echo json_encode($response);
?>