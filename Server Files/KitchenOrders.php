<?php 
      
     $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
     $status = "Pending";
     $sql = "SELECT * FROM orders WHERE datePlaced >= CURRENT_DATE() AND status ='". $status. "' ORDER BY datePlaced ASC";
	 
	 $request = mysqli_query($con,$sql);
	 
	 $result = array();

     while($res= mysqli_fetch_array($request))
	 {
		 array_push
		 (
		 	$result,array
		 	(
				 "orderId"=>$res['orderId'],
				 "employeeId"=>$res['employeeId'],
				 "tableId"=>$res['tableNo'],
				 "datePlaced"=>$res['datePlaced'],
				 "totalPrice"=>$res['totalPrice'],
				 "status"=>$res['status']
			 )
		 );
	 }

	 echo json_encode(array("result"=>$result));
	 mysqli_close($con);
 ?>