<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $title = $_POST["title"];
	$content = $_POST["content"];
	$noteId = $_POST["noteId"];

    //Creating sql query 
	 $sql = "UPDATE notes SET title = '$title', content = '$content' WHERE noteId = '$noteId'";
	 
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