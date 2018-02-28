<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$orderId = $_POST["orderId"];
	$itemID = $_POST["itemID"];
	$updatedStatus = "Completed";

    //Creating sql query 
	 $sql = "UPDATE orderItems SET status = '$updatedStatus' WHERE itemID = '$itemID' AND orderId = '$orderId'";
	 
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