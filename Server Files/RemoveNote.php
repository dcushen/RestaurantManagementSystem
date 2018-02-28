<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");
    
    $noteId = $_POST["noteId"];

    $statement = mysqli_prepare($con, "DELETE FROM notes WHERE noteId = ?");
    mysqli_stmt_bind_param($statement, "s", $noteId);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>