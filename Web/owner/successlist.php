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
  $username = $_SESSION['user'];
  if (isset($_POST['item1']) && isset($_POST['per1'])&& isset($_POST['image1']))
  {
    $item1 = $_POST['item1'];
    $per1 = $_POST['per1'];
    $imagename1 = $_FILES["image1"]["name"];
			$type = $_FILES["image1"]["type"];
			$tmp = $_FILES["image1"]["tmp_name"];
			$error = $_FILES["image1"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }
  if (isset($_POST['item2']) && isset($_POST['per2'])&& isset($_POST['image2']))
  {
    $item2 = $_POST['item2'];
    $per2 = $_POST['per2'];
    $imagename2 = $_FILES["image2"]["name"];
			$type = $_FILES["image2"]["type"];
			$tmp = $_FILES["image2"]["tmp_name"];
			$error = $_FILES["image2"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item3']) && isset($_POST['per3'])&& isset($_POST['image3']))
  {
    $item3 = $_POST['item3'];
    $per3 = $_POST['per3'];
    $imagename3 = $_FILES["image3"]["name"];
			$type = $_FILES["image3"]["type"];
			$tmp = $_FILES["image3"]["tmp_name"];
			$error = $_FILES["image3"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item4']) && isset($_POST['per4'])&& isset($_POST['image4']))
  {
    $item4 = $_POST['item4'];
    $per4 = $_POST['per4'];
    $imagename4 = $_FILES["image4"]["name"];
			$type = $_FILES["image4"]["type"];
			$tmp = $_FILES["image4"]["tmp_name"];
			$error = $_FILES["image4"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item5']) && isset($_POST['per5'])&& isset($_POST['image5']))
  {
    $item5 = $_POST['item5'];
    $per5 = $_POST['per5'];
    $imagename5 = $_FILES["image5"]["name"];
			$type = $_FILES["image5"]["type"];
			$tmp = $_FILES["image5"]["tmp_name"];
			$error = $_FILES["image5"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item6']) && isset($_POST['per6'])&& isset($_POST['image6']))
  {
    $item6 = $_POST['item6'];
    $per6 = $_POST['per6'];
    $imagename6 = $_FILES["image6"]["name"];
			$type = $_FILES["image6"]["type"];
			$tmp = $_FILES["image6"]["tmp_name"];
			$error = $_FILES["image6"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item7']) && isset($_POST['per7'])&& isset($_POST['image7']))
  {
    $item7 = $_POST['item7'];
    $per7 = $_POST['per7'];
    $imagename7 = $_FILES["image7"]["name"];
			$type = $_FILES["image7"]["type"];
			$tmp = $_FILES["image7"]["tmp_name"];
			$error = $_FILES["image7"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item8']) && isset($_POST['per8'])&& isset($_POST['image8']))
  {
    $item8 = $_POST['item8'];
    $per8 = $_POST['per8'];
    $imagename8 = $_FILES["image8"]["name"];
			$type = $_FILES["image8"]["type"];
			$tmp = $_FILES["image8"]["tmp_name"];
			$error = $_FILES["image8"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item9']) && isset($_POST['per9'])&& isset($_POST['image9']))
  {
    $item9 = $_POST['item9'];
    $per9 = $_POST['per9'];
    $imagename9 = $_FILES["image9"]["name"];
			$type = $_FILES["image9"]["type"];
			$tmp = $_FILES["image9"]["tmp_name"];
			$error = $_FILES["image9"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }if (isset($_POST['item10']) && isset($_POST['per10'])&& isset($_POST['image10']))
  {
    $item10 = $_POST['item10'];
    $per10 = $_POST['per10'];
    $imagename10 = $_FILES["image10"]["name"];
			$type = $_FILES["image10"]["type"];
			$tmp = $_FILES["image10"]["tmp_name"];
			$error = $_FILES["image10"]["error"];
			if ($error > 0) {
				echo " not uploaded";
			}
			else if(($type == "image/jpeg") || ($type == "image/png"))
			{
				move_uploaded_file($tmp, "/Users/varis/.bitnami/stackman/machines/xampp/volumes/root/htdocs/owner/".$imagename);
				//echo "uploaded";

      }
  }
  $statement = "insert into list values('".$username."','".$item1."','".$per1."','".$imagename1."','".$item2."','".$per2."','".$imagename2."','".$item3."','".$per3."','".$imagename3."','".$item4."','".$per4."','".$imagename4."','".$item5."','".$per5."','".$imagename5."','".$item5."','".$per5."','".$imagename5."','".$item6."','".$per6."','".$imagename6."','".$item7."','".$per7."','".$imagename7."','".$item8."','".$per8."','".$imagename8."','".$item9."','".$per9."','".$imagename9."','".$item10."','".$per10."','".$imagename10."')";

			$result = mysqli_query($db,$statement);

//      include 'footer.php';
?>
