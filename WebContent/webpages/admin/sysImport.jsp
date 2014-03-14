<%@ page import="main.*"%>
<%@ page import="configs.*"%>
<%@ page import="persistence.*"%>
<%@ page import="java.text.DecimalFormat"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<%
	HttpSession sess = request.getSession(false);
	String userId;
	String userName = "";
	String password;
	String userType = "";
	if (sess != null && !sess.isNew()) {
		userId = (String.valueOf(sess.getAttribute("id")));
		if (userId == null || userId.equals("")
				|| userId.equals("null")) {
			userName = String.valueOf(sess.getAttribute("userName"));
			password = String.valueOf(sess.getAttribute("password"));
			userType = String.valueOf(sess.getAttribute("userType"));
			if (userName == null || userName.equals("")
					|| userName.equals("null") || password == null
					|| password.equals("") || password.equals("null")) {
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
	<h3 class="col-md-offset-4 col-md-7 text-left"><%=Strings.IMPORT%></h3>
	<br /> <br /> <br />

	<form name="upload" method="post" action="/JPASprint1/ImportServlet"
		enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="file" value="Import" name="ImportFile"
					accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					required />
			</div>
		</div>
		<br /> <br />
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<button type="submit" class="btn btn-primary"><%=Strings.UPLOAD%></button>
			</div>
		</div>
	</form>

	<%
		if (DatabaseGenerator.datasetUploaded()) {
			DecimalFormat dFormatter = new DecimalFormat("#,###,###");
	%>
	<br />
	<p>Successfully imported the database.</p>
	<br />
	<p><%=dFormatter.format(ErrorEventConfig.errorEvents.size())%>
		ErrorEvents added to database. (Total:
		<%=dFormatter.format(PersistenceUtil
						.numberOfErrorEvents())%>+ ) <br />
		<%=dFormatter.format(ErrorEventConfig.invalidErrorEvents
						.size())%>
		ErrorEvents removed due to inconsistencies. (Total:
		<%=dFormatter.format(PersistenceUtil
						.numberOfInvalidErrorEvents())%></p>
	<%
		}
		DatabaseGenerator.datasetConfirmed();
	%>

</div>

<jsp:include page="../templates/footer.jsp" />