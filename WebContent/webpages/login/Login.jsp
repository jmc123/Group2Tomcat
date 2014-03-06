<!DOCTYPE>
<html>
	<head>
		<title>			</title>
		

		<link rel="shortcut icon" type="image/x-icon" href="../../images/favicon.ico">
		<link href="../../css/bootstrap.min.css" rel="stylesheet">
		<link href="../../css/styles.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>

	</head>
	<body>
	
		<div class="container" id="main">
			<div class="row">
				<div class="col-md-3 text-center">
					<img src="logo.jpg" height="100" width="100" id="logo">
				</div>
			</div>
			<div class="col-md-12">
				<hr />
			</div>
			
			<div class="row">
				<div class="col-md-3 text-center">
				</div>
				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-3 col-md-6 text-center">Login</h3>
					<br /><br /><br />
					<form method="get" action="" class="form-horizontal" role="form" name="loginForm" onsubmit="return validateLogin();">
						<div class="form-group">
							<label for="id" class="col-md-4 control-label">ID:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="id" name="id">
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-4 control-label">Password:</label>
							<div class="col-md-4">
								<input type="password" class="form-control" id="pass" name="password">
							</div>
						</div>

						<br />
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<a type="submit" class="btn btn-primary" href="addUser.html">Login</a></button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
		</div>
		<footer class="container">
			<div class="col-md-12 text-left" id="text">
				<p>&copy; Jason Costello, Tim Coghlan, Danny Flynn, Gavin Hughes, James McManus, Arouge Mehdi.</p> 
			</div>
				
		</footer>
	
	
	</body>
</html>
