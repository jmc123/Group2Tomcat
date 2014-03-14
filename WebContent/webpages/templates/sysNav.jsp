<%@ page import="main.*"%>
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
<div class="row">
	<div class="col-md-3 text-left" id="border">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="sysHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a href="sysImport.jsp"><span
					class="glyphicon glyphicon-save"></span> <%=Strings.IMPORT%></a></li>
			<li><a href="sysAddUser.jsp"><span
					class="glyphicon glyphicon-user"></span> <%=Strings.ADD_USER%></a></li>
			<li><a href="sysListUsers.jsp"><span
					class="glyphicon glyphicon-user"></span> <%=Strings.SHOW_USERS%></a></li>
		</ul>
	</div>