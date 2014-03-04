<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-4 col-md-7 text-left">Create User Role</h3>
					<br /><br /><br />
					<form method="get" action="" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="role" class="col-md-4 control-label">Role:</label>
							<div class="col-md-4">
								<select class="form-control" name="role">
									<option>Customer Service Rep</option>
									<option>Support Engineer</option>
									<option>Network Management Engineer</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="userid" class="col-md-4 control-label">ID:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="userid" name="username">
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
								<button type="submit" class="btn btn-primary">Create User</button>
							</div>
						</div>
					</form>
				</div>
				
<jsp:include page="../templates/footer.jsp" />