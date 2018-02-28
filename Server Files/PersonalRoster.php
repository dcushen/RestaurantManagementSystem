<?php
    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$shiftId = $_POST["shiftId"];
    $employeeId = $_POST["employeeId"];
    
    $sql= "SELECT * FROM roster WHERE shiftId= '$shiftId' AND employeeId = '$employeeId'";
    $request = mysqli_query($con,$sql);
	 
	 $result = array();

     while($res= mysqli_fetch_array($request))
	 {
		 array_push
		 (
		 	$result,array
		 	(
				 "Id"=>$res['Id'],
                                 "shiftId"=>$res['shiftId'],
				 "startTime"=>$res['startTime'],
				 "endTime"=>$res['endTime'],
				 "employeeId"=>$res['employeeId']
			 )
		 );
	 }

	 echo json_encode(array("result"=>$result));
	 mysqli_close($con);
?>