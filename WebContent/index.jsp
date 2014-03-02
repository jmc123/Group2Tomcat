<html>
<head>
<style>
body {
	background-color: gray;
}
</style>
<script>
	function validateForm() {
		var x = document.forms["flogin"]["userName"].value;
		var y = document.forms["flogin"]["password"].value;
		if (x == null || x == "" || y == null || y == "") {
			alert("Username and Password must be filled out");
			return false;
		}
	}
</script>
<title>Login page</title>
</head>
<body>
	<a href="http://www.ericsson.com/ie" target="_parent"><img
		STYLE="position: absolute; TOP: 35px; LEFT: 110px; WIDTH: 110px; HEIGHT: 110px"
		src="Ericsson_logo.png" alt="Ericsson logo" /></a>
	<a href="http://www.dit.ie" target="_parent"><img
		STYLE="position: absolute; TOP: 175px; LEFT: 110px; WIDTH: 110px; HEIGHT: 110px"
		src="DIT_logo.png" alt="DIT logo" /></a>
	<div style="text-align: center;">
		<div
			style="box-sizing: border-box; display: inline-block; width: auto; max-width: 480px; background-color: #FFFFFF; border: 2px solid #FF0505; border-radius: 5px; box-shadow: 0px 0px 8px #FF0505; margin: 50px auto auto;">
			<div
				style="background: #0195FE; border-radius: 5px 5px 0px 0px; padding: 15px;">
				<span
					style="font-family: verdana, arial; color: #D4D4D4; font-size: 1.00em; font-weight: bold;">Login
					page</span>
			</div>
			<div style="background:; padding: 15px">
				<style type="text/css" scoped>
td {
	text-align: left;
	font-family: verdana, arial;
	color: #000000;
	font-size: 1.00em;
}

input {
	border: 1px solid #CCCCCC;
	border-radius: 5px;
	color: #666666;
	display: inline-block;
	font-size: 1.00em;
	padding: 5px;
	width: 100%;
}

input[type="button"],input[type="reset"],input[type="submit"] {
	height: auto;
	width: auto;
	cursor: pointer;
	box-shadow: 0px 0px 5px #FF0505;
	float: right;
	margin-top: 10px;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}

.error {
	font-family: verdana, arial;
	color: #000000;
	font-size: 1.00em;
}
</style>
				<table class='center'>
					<form name="flogin" action="LoginServlet" onsubmit="return validateForm()" method="GET">
						User: <input type="text" name="userName" />
						Password: <input type="password" name="password" />
						<input type="reset" value="Cancel" />
						<input type="submit" value="Login" />
					</form>
				</table>
			</div>
		</div>
	</div>
</body>

</body>
</html>