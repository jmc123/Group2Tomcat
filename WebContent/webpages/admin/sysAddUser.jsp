<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<%
	HttpSession sess = request.getSession(false);
	String userId;
	String userName="";
	String password;
	String userType="";
	if (sess != null && !sess.isNew()) {
		userId = (String.valueOf(sess.getAttribute("id")));
		if (userId == null || userId.equals("")
				|| userId.equals("null")) {
			userName = String.valueOf(sess.getAttribute("userName"));
			password = String.valueOf(sess.getAttribute("password"));
			userType = String.valueOf(sess.getAttribute("userType"));
			if (userName == null || userName.equals("") || userName.equals("null")
					|| password == null || password.equals("")|| password.equals("null")) {
				response.sendRedirect("/JPASprint1");
			} else {
				sess.setAttribute("id", userId);
				sess.setAttribute("password", password);
			}

		}

		else if (userId.equals("2")) {
			response.sendRedirect(request.getContextPath()
					+ "/webpages/networkManEng/nmeHome.jsp");
		} else if (userId.equals("3")) {
			response.sendRedirect(request.getContextPath()
					+ "/webpages/supportEng/seHome.jsp");
		} else if (userId.equals("4")) {
			response.sendRedirect(request.getContextPath()
					+ "/webpages/customerRep/csHome.jsp");
		} 
	}
%>
<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-5 col-md-8 text-left"><em><%=Strings.CREATE_USER%></em></h3>
					<br /><br /><br />
					<form method="POST" name="register" action="/JPASprint1/RegisterServlet"
							onsubmit="return validateInputsAreValid()" class="form-horizontal" >
							<div class="col-md-6 text-center">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-8">
								<select class="form-control" name="role" id="role">
									<option value="0"><%=Strings.SELECT_ROLE%></option>
									<option value="2"><%=Strings.NME%></option>
									<option value="3"><%=Strings.SE%></option>
									<option value="4"><%=Strings.CSR%></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-8">
								<input type="text" class="form-control" id="userName" name="userName" placeholder="<%=Strings.PH_USERNAME%>" required />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-8">
								<input type="password" class="form-control" id="password" name="password" placeholder="<%=Strings.PH_PASSWORD%>" required />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-8">
								<input type="password" class="form-control" id="confirm" name="confirm" placeholder="<%=Strings.PH_CONFIRM%>" required />
							</div>
						</div>
						</div>
						<div class="col-md-6 text-center">
						<div class="form-group">
					
							<div class="col-md-offset-1 col-md-8">
								<input type="text" class="form-control" id="fname" name="fname" placeholder="<%=Strings.PH_FNAME%>" required />
							</div>
						</div>
						<div class="form-group">
						
							<div class="col-md-offset-1 col-md-8">
								<input type="text" class="form-control" id="lname" name="lname" placeholder="<%=Strings.PH_LNAME%>" required />
							</div>
						</div>
						<div class="form-group">
							
							<div class="col-md-offset-1 col-md-8">
								<input type="text" class="form-control" id="email" name="email" placeholder="<%=Strings.PH_EMAIL%>" required 
									title="<%=Strings.TT_EMAIL%>" data-toggle="tooltip" data-placement="bottom" />
							</div>
						</div>
						<div class="form-group">
							
							<div class="col-md-offset-1 col-md-8">
								<input type="text" class="form-control" id="phone" name="phone" placeholder="<%=Strings.PH_PHONE%>" required
								title="<%=Strings.TT_PHONE%>" data-toggle="tooltip" data-placement="bottom" />
							</div>
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