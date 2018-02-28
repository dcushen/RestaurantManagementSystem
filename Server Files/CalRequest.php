<?php
	$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$IngredientName= $_POST["IngredientName"];

    $sql = "SELECT * FROM ingredients WHERE IngredientName ='". $IngredientName. "'";

	$request = mysqli_query($con,$sql);
	 
	$result = array();

    while($res= mysqli_fetch_array($request))
	{
	    array_push
		(
		 	$result,array
		 	(
				 "IngredientName"=>$res['IngredientName'],
				 "Type"=>$res['Type'],
				 "Cal"=>$res['Cal']
			 )
		);
	}

	echo json_encode(array("result"=>$result));
	mysqli_close($con);
?>