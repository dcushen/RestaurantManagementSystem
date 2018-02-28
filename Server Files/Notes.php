<?php 
     $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
     $sql = "SELECT * FROM notes ORDER BY dateWritten ASC";
	 
	 $request = mysqli_query($con,$sql);
	 
	 $result = array();
	 
         while($res= mysqli_fetch_array($request))
	 {
	 array_push
	 (
	 	$result,array
	 	(
			 "title"=>$res['title'],
			 "content"=>$res['content'],
			 "noteId"=>$res['noteId'],
			 "employeeId"=>$res['employeeId'],
			 "dateWritten"=>$res['dateWritten']
		 )
	 );
	 }
	 echo json_encode(array("result"=>$result));
	 mysqli_close($con);
 ?>