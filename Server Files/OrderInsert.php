<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $orderId = $_POST["orderId"];
    $employeeId = $_POST["employeeId"];
    $tableNo= $_POST["tableId"];
    $datePlaced = date('Y-m-d H:i:s');
    $totalPrice = $_POST["totalPrice"];
    $status = "Pending";

    $statement = mysqli_prepare($con, "INSERT INTO orders (orderId, employeeId, tableNo, datePlaced, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssssss", $orderId, $employeeId, $tableNo, $datePlaced, $totalPrice, $status);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>