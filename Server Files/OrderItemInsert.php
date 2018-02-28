<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $itemID = $_POST["itemID"];
    $Name = $_POST["Name"];
    $Price = $_POST["Price"];
    $datePlaced = date('Y-m-d H:i:s');
    $quantity = 1;
    $orderId = $_POST["OrderId"];
    $status = "Pending";

    $statement = mysqli_prepare($con, "INSERT INTO orderItems (itemID, Name, Price, datePlaced, quantity, orderId, status) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + 1");
    mysqli_stmt_bind_param($statement, "sssssss", $itemID, $Name, $Price, $datePlaced, $quantity, $orderId, $status);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>