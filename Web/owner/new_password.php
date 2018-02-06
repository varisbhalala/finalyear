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
  if (isset($_POST['otp']))
  {
    $otp = trim($_POST['otp']);

    $sql = "SELECT * FROM register WHERE otp = '$otp'";
    $result = mysqli_query($db,$sql);
    $row = $result->fetch_assoc();
    $_SESSION['find'] = $row['username'];
    $num_rows = mysqli_num_rows($result);
    //echo $num_rows;
    if($num_rows == 1)
    {

      $statement = "UPDATE register SET otp = 'NULL' WHERE otp = '$otp'";

        header('location:/owner/enter_newpass.php');


    }
    else
    {
      header('location:/owner/forgotpassword.php');
    }
  }

//      include 'footer.php';
?>
