<?php
	$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$noteId = $_POST["noteId"];

    $sql = "SELECT * FROM notes WHERE noteId ='". $noteId. "'";

	$request = mysqli_query($con,$sql);
	 
	$result = array();

    while($res= mysqli_fetch_array($request))
	{
	    array_push
		(
		 	$result,array
		 	(
				"noteId"=>$res['noteId'],
			 	"title"=>$res['title'],
			 	"dateWritten"=>$res['dateWritten'],
			 	"content"=>$res['content'],
			 	"employeeId"=>$res['employeeId']
			 )
		);
	}

	echo json_encode(array("result"=>$result));
	mysqli_close($con);
?>