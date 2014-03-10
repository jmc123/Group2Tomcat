<!DOCTYPE>
<html>
	<head>
		<title>Group2 Project Page</title>
		

		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/styles.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
	
		<div class="container" id="main">
			<div class="row">
				<div class="col-md-3 text-center">
					<img src="images/logo.jpg" height="100" width="100" id="logo">
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
					<form method="get" action="/JPASprint1/LoginServlet" class="form-horizontal" name="loginForm">
						<div class="form-group">
							<label for="id" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="userName" name="userName" placeholder="Username" required />
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="password" class="form-control" id="pass" name="password" placeholder="Password" required />
							</div>
						</div>

						<br />
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<button class="btn btn-primary" type="submit">Login</button>
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
