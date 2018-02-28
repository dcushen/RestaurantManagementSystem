<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $itemID = $_POST["itemID"];

    $statement = mysqli_prepare($con, "DELETE FROM menu WHERE itemID = ?");
    mysqli_stmt_bind_param($statement, "s", $itemID);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>