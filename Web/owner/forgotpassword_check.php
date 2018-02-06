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
  if (isset($_POST['email']))
  {
    $email = trim($_POST['email']);

    $sql = "SELECT email FROM register WHERE email = '$email'";
    $result = mysqli_query($db,$sql);
    $row = $result->fetch_assoc();
    $num_rows = mysqli_num_rows($result);
    //echo $num_rows;
    if($num_rows == 1)
    {
      //echo 'logged in';
      $token = rand(100000,999999);
      $statement = "UPDATE register SET token = '$token' WHERE email = '$email'";
      $to = $email;
      $subject = "OTP indoor tracking";
      $txt = $token;
      $headers = "From: varisbhalala555@gmail.com\r\n";


      mail($to,$subject,$txt,$headers);
      $result1 = mysqli_query($db,$sql);
      $num_rows1 = mysqli_num_rows($result1);
      if ($num_rows1 == 1) {
        header('location:/owner/update.php');
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

//      include 'footer.php';
?>
