<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $employeeId = $_POST["employeeId"];
    $formattedTime = $_POST["formattedTime"];
    $todaysDate = date('Y-m-d');

    //Creating sql query 
    $sql = "UPDATE clock SET endShift = '$formattedTime' WHERE todaysDate = '$todaysDate' AND employeeId = '$employeeId'";
    
    //Updating database table 
    if(mysqli_query($con,$sql))
    {
        $response["success"] = true;
        echo json_encode($response);
    }
    else
    {
        $response["success"] = false;  
        echo json_encode($response);
    }

    //closing connection 
    mysqli_close($con);
?>