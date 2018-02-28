<?php
	$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$orderId = $_POST["orderId"];

    $sql = "SELECT * FROM orderItems WHERE orderId ='". $orderId. "'";

	$request = mysqli_query($con,$sql);
	 
	$result = array();

    while($res= mysqli_fetch_array($request))
	{
	    array_push
		(
		 	$result,array
		 	(
				 "itemID"=>$res['itemID'],
				 "Name"=>$res['Name'],
				 "Price"=>$res['Price'],
                                 "datePlaced"=>$res['datePlaced'],
				 "quantity"=>$res['quantity'],
				 "orderId"=>$res['orderId'],
                                 "status"=>$res['status']
			 )
		);
	}

	echo json_encode(array("result"=>$result));
	mysqli_close($con);
?>