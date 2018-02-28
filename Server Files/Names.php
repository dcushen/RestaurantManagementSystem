<?php
    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $sql= "SELECT employeeId FROM staff";
    $request = mysqli_query($con,$sql);
	 
	 $result = array();

     while($res= mysqli_fetch_array($request))
	 {
		 array_push
		 (
		 	$result,array
		 	(
				"employeeId"=>$res['employeeId']
			 )
		 );
	 }

	 echo json_encode(array("result"=>$result));
	 mysqli_close($con);
?>