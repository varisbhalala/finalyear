<?php
session_start();
  $db = mysqli_connect('localhost','root','');
  if (!$db)
  {
  	print "<h1>Unable to Connect to MySQL</h1>";
  }
  $dbname = 'WT';
  $btest = mysqli_select_db($db,$dbname);
  if (!$btest)
  {
  	print "<h1>Unable to Select the Database</h1>";
  }

    $mobile = $_SESSION['user'];
    $otp = $_POST['otp'];
    $sql = "SELECT otp FROM login WHERE mobile_number = '$mobile'";
    $result = mysqli_query($db,$sql);
    $row = $result->fetch_assoc();
    if ($otp == $row['otp']) {
      echo 'registration successful';
    }
  ?>
