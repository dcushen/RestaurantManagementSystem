<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$orderId = $_POST["orderId"];
	$updatedStatus = "Completed";

    //Creating sql query 
	$sql = "UPDATE orders SET status = '$updatedStatus' WHERE orderId = '$orderId'";
	
	//Updating database table 
	if(mysqli_query($con,$sql))
	{
	 	$response["success"] = true;  

	 	//Creating sql query 
	 	$secondSQL = "UPDATE orderItems SET status = '$updatedStatus' WHERE orderId = '$orderId'";
        mysqli_query($con,$secondSQL);
        
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