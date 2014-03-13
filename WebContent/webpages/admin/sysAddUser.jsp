<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-4 col-md-7 text-left"><%=Strings.CREATE_USER%></h3>
					<br /><br /><br />
					<form method="POST" name="register" action="/JPASprint1/RegisterServlet"
							onsubmit="return validatePasswordsMatch()" class="form-horizontal" >
						<div class="form-group">
							<div class="col-md-4">
								<select class="form-control" name="role">
									<option value="4"><%=Strings.CSR%></option>
									<option value="3"><%=Strings.SE%></option>
									<option value="2"><%=Strings.NME%></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="userid" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="userName" name="userName" placeholder="<%=Strings.PH_USERNAME%>" required />
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="password" class="form-control" id="password" name="password" placeholder="<%=Strings.PH_PASSWORD%>" required />
							</div>
						</div>
						<div class="form-group">
							<label for="pass" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="password" class="form-control" id="confirm" name="confirm" placeholder="<%=Strings.PH_CONFIRM%>" required />
							</div>
						</div>

						<br />
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<button type="submit" class="btn btn-primary"><%=Strings.CREATE_USER%></button>
							</div>
						</div>
					</form>
				</div>
				
<jsp:include page="../templates/footer.jsp" />