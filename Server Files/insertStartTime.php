<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $employeeId = $_POST["employeeId"];
    $formattedTime = $_POST["formattedTime"];
    $todaysDate = date('Y-m-d');

    $query = mysqli_prepare($con, "INSERT INTO clock VALUES('$employeeId','$todaysDate','$formattedTime','')");
    mysqli_stmt_execute($query);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>