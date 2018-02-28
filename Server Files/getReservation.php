<?php
         $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
         $sql = "SELECT * FROM reservations";

	 $request = mysqli_query($con,$sql);

	 $result = array();

         while($res= mysqli_fetch_array($request))
	 {
	 array_push
	 (
	 	$result,array
	 	(

			 "name"=>$res['name'],
			 "date"=>$res['date'],
			 "time"=>$res['time'],
			 "table"=>$res['tableNo'],
			 "phone"=>$res['phone'],
			 "bookID"=>$res['bookID']


		 )
	 );
	 }
	 echo json_encode(array("result"=>$result));
	 mysqli_close($con);
 ?>