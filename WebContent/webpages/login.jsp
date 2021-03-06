<%@ page import="main.*"%>
<!DOCTYPE>
<html>
	<head>
		<title><%=Strings.TITLE%></title>
		

		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/styles.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
	
		<div class="container" id="main">
		<div class="row">
			<div class="col-md-2 text-center">
				<img src="images/logo.jpg" height="100" width="100" id="logo">
			</div>
			<div class="col-md-10" id="banner"
				style="background-image: url(images/banner.png); height: 120px; width: 950px;">
			</div>
			<div class="col-md-12">
				<hr />
			</div>

			<div class="row">

				<div class="col-md-12 text-center">
					<h3 class="col-md-offset-3 col-md-6 text-center"><%=Strings.LOGIN%></h3>
					<br />
					<br />
					<br />
					<form method="get" action="/JPASprint1/LoginServlet"
						class="form-horizontal" name="loginForm">
						<div class="form-group">
							<label for="id" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="userName"
									name="userName" placeholder="<%=Strings.PH_USERNAME%>" required />
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="password" class="form-control" id="pass"
									name="password" placeholder="<%=Strings.PH_PASSWORD%>" required />
							</div>
						</div>

						<br />
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<button class="btn btn-primary" type="submit"><%=Strings.LOGIN%></button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
		</div>
		<footer class="container">
			<div class="col-md-12 text-left" id="text">
			<img src="images/bottomlogo.png" height="50" width="50" id="bottomlogo">
				<span><%=Strings.COPYRIGHT_AND_NAMES%></span> 
			</div>
				
		</footer>
	
	
	</body>
</html>
