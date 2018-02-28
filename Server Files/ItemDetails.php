<?php
	$con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

	$itemID = $_POST["itemID"];

    $sql = "SELECT * FROM menu WHERE itemID ='". $itemID. "'";

	$request = mysqli_query($con,$sql);
	 
	$result = array();

    while($res= mysqli_fetch_array($request))
	{
	    array_push
		(
		 	$result,array
		 	(
				"itemID"=>$res['itemID'],
			 	"Category"=>$res['Category'],
			 	"Name"=>$res['Name'],
			 	"Ingredients"=>$res['Ingredients'],
			 	"Allergy"=>$res['Allergy'],
			 	"Vegan"=>$res['Vegan'],
			 	"Vegetarian"=>$res['Vegetarian'],
			 	"Cal"=>$res['Cal'],
			 	"Spicy"=>$res['Spicy'],
			 	"Servings"=>$res['Servings'],
			 	"Price"=>$res['Price']
			 )
		);
	}

	echo json_encode(array("result"=>$result));
	mysqli_close($con);
?>