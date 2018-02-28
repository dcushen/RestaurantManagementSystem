<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $Category = $_POST["Category"];
	$itemID = $_POST["itemID"];
	$Name = $_POST["Name"];
	$Ingredients = $_POST["Ingredients"];
	$Allergy = $_POST["Allergy"];
	$Vegan = $_POST["Vegan"];
	$Vegetarian = $_POST["Vegetarian"];
	$Cal = $_POST["Cal"];
	$Spicy = $_POST["Spicy"];
	$Price = $_POST["Price"];
	$Servings = $_POST["Servings"];

    //Creating sql query 
	 $sql = "UPDATE menu SET Category = '$Category', Name = '$Name', Ingredients = '$Ingredients', Allergy = '$Allergy', Vegan = '$Vegan', Vegetarian = '$Vegetarian', Cal = '$Cal', Spicy = '$Spicy', Price = '$Price', Servings = '$Servings' WHERE itemID = '$itemID'";
	 
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