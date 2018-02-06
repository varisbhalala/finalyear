<?php
  session_start();
  $db = mysqli_connect('localhost','root','');
  if (!$db)
  {
  	print "<h1>Unable to Connect to MySQL</h1>";
  }
  $dbname = 'indoor';
  $btest = mysqli_select_db($db,$dbname);
  if (!$btest)
  {
  	print "<h1>Unable to Select the Database</h1>";
  }
  if(isset($_POST['mobile']))
  {


    $mobile = $_POST["mobile"];
    $_SESSION['user'] = $mobile;
    $senderId = "WIFIMA";

    $rndno=rand(1000, 9999);
    $message = urlencode("otp number.".$rndno);


    $username = "varispatel555@gmail.com";
  	$hash = "ce4c87f08d0b98b6d99a05458ac29fb803b472817073246cd07d4808cf1bac30";

  	// Config variables. Consult http://api.textlocal.in/docs for more info.
  	$test = "1";

  	// Data for text message. This is the text message data.
  	$sender = "WIFIMA"; // This is who the message appears to be from.
  	$numbers = $mobile; // A single number or a comma-seperated list of numbers
  	$message = "OTP is:".$rndno;
    $statement = "insert into login values( '".$mobile."' , '".$rndno."' )";
    $result = mysqli_query($db,$statement);
  	// 612 chars or less
  	// A single number or a comma-seperated list of numbers
  	$message = urlencode($message);
  	$data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$mobile."&test=".$test;
    
  	$ch = curl_init('http://api.textlocal.in/send/?');
  	curl_setopt($ch, CURLOPT_POST, true);
  	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
  	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  	$result = curl_exec($ch); // This is the result from the API
    if ($result) {
      echo "sent";
    }
  	curl_close($ch);

    header('location:/Login/confirm.php');
  }
 ?>
