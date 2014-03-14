<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->
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


<%
	long startTime = System.nanoTime();
	List<User> users = PersistenceUtil.findAllUsers();
%>
<div class="col-md-9 text-center">
<div class="col-md-10 text-center">
	<h3 class="text-center"><em>
		<%=Strings.ALL_USERS%></em> <br />
	</h3>
	<table class=" table table-striped table-bordered">
		<tr>
			<th class="text-center"><%=Strings.USERNAME%></th>
			<th class="text-center"><%=Strings.USER_TYPE%></th>
		</tr>
		<%
			for (User user : users) {
		%>
		<tr>
			<td class="text-center"><%=user.getPrimaryKey()%></td>
			<td class="text-center"><%=user.getUserType().getDesc()%></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
</div>
<jsp:include page="../templates/footer.jsp" />