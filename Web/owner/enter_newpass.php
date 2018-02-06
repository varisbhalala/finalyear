<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
		.jumbotron{

			text-align: center;
		}
		.login{
			text-align: center;
		}
	</style>
</head>
<body>

<div class="jumbotron">
  <h3>Indoor Tracking System</h3>
  <p>Shop Owner New Password</p>
</div>

<div class="login">
	<h4>New Password</h4>
	<form class="form-horizontal" action="save_newpass.php" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">New Password</label>
      <div class="col-sm-8">
        <input type="password" class="form-control" id="email" placeholder="Enter New Password" name="new_password">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-8">
        <input type="password" class="form-control" id="pwd" placeholder="Confirm password" name="confirm_password">
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-1 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>

  </form>
</div>

</body>
</html>
