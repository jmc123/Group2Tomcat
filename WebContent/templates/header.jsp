<!DOCTYPE>
<html>
	<head>
		<title>Group 2 Web App</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/styles.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>

	</head>
	<body>
	
		<div class="container" id="main">
			<div class="row">
				<div class="col-md-3 text-center">
					<img src="logo.jpg" height="100" width="100" id="logo">
				</div>
				<div class="col-md-7" id="margintext">
					<p class="text-right center"><span class="glyphicon glyphicon-user"></span> Logged in as <a href="#" class="navbar-link">${user.userType}</a></p><!-- get from session -->
				</div>
				<div class="col-md-2" id="marginbt">
					<a href="login.jsp" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
				</div>
			</div>
			<div class="col-md-12">
				<hr />
			</div>