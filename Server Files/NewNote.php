<?php 

    $con = mysqli_connect("localhost", "id155858_admin", "yU85M638", "id155858_user");

    $title = $_POST["title"];
    $content = $_POST["content"];
    $noteId= $_POST["noteId"];
    $dateWritten = date('Y-m-d H:i:s');
    $employeeId = $_POST["employeeId"];

    $statement = mysqli_prepare($con, "INSERT INTO notes (noteId, title, content, dateWritten, employeeId) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss", $noteId, $title, $content, $dateWritten, $employeeId);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);

    mysqli_close($con);
 ?>