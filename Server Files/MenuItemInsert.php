<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $itemID = $_POST["itemID"];
    $Category = $_POST["Category"];
    $Name = $_POST["Name"];
    $Ingredients = $_POST["Ingredients"];
    $Allergy = $_POST["Allergy"];
    $Vegan = $_POST["Vegan"];
    $Vegetarian = $_POST["Vegetarian"];
    $Cal = $_POST["Cal"];
    $Spicy = $_POST["Spicy"];
    $Servings = $_POST["Servings"];
    $Price = $_POST["Price"];

    $statement = mysqli_prepare($con, "INSERT INTO menu (itemID, Category, Name, Ingredients, Allergy, Vegan, Vegetarian, Cal, Spicy, Servings, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssssssssss", $itemID, $Category, $Name, $Ingredients, $Allergy, $Vegan, $Vegetarian, $Cal, $Spicy, $Servings, $Price);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>