<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $bookingId = $_POST["bookingId"];

    $statement = mysqli_prepare($con, "DELETE FROM reservations WHERE bookID = ?");
    mysqli_stmt_bind_param($statement, "s", $bookingId);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>