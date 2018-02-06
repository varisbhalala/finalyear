<?php
  session_start();
 //include 'header.php';
  $db = mysqli_connect('localhost','root','');
  if (!$db)
  {
  	print "<h1>Unable to Connect to MySQL</h1>";
  }
  $dbname = 'owner';
  $btest = mysqli_select_db($db,$dbname);
  if (!$btest)
  {
  	print "<h1>Unable to Select the Database</h1>";
  }
  if (isset($_POST['new_password']) && isset($_POST['new_password']))
  {
    $new = $_POST['new_password'];
    $confirm = $_POST['new_password'];
    if ($new == $confirm) {



    $sql = "SELECT password FROM register WHERE username = $_SESSION['find']";
    $result = mysqli_query($db,$sql);
    $row = $result->fetch_assoc();
    $num_rows = mysqli_num_rows($result);
    //echo $num_rows;
    if($num_rows == 1)
    {
      //echo 'logged in';
      $token = rand(100000,999999);
      $statement = "UPDATE register SET password = '$new' WHERE username = $_SESSION['find']";

      $result1 = mysqli_query($db,$sql);
      $num_rows1 = mysqli_num_rows($result1);
      if ($num_rows1 == 1) {
        header('location:/owner/login.php');
      }
      else{
        header('location:/owner/forgotpassword.php');
      }

    }
    else
    {
      header('location:/owner/forgotpassword.php');
    }
  }
  }

//      include 'footer.php';
?>
