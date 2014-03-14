<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		userName = cookie.getValue();
	}
	User userDetails = PersistenceUtil.findUserByUsername(userName);
	String userType = userDetails.getUserType().getDesc();
%>


<%
	long startTime = System.nanoTime();
	List<User> users = PersistenceUtil.findAllUsers();
%>
<div class="col-md-9 text-center">
<div class="col-md-10 text-center">
	<h3 class="text-center">
		<%=Strings.ALL_USERS%> <br />
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
		<%
			long timeTakenInNanos = System.nanoTime() - startTime;
			String timeTaken = String.format("<p>" + Strings.QUERY_EXECUTION_TIME + "<p>",
					(double) timeTakenInNanos / 1000000);
		%>

	</table>
	<h3 class="text-center"><%=timeTaken%></h3>
</div>
</div>
<jsp:include page="../templates/footer.jsp" />