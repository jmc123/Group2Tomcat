
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
	List<Object[]> userDetails = PersistenceUtil
			.findPasswordAndUserTypeByUsername(userName);
	String userType = ((UserType) userDetails.get(0)[1]).getDesc();
%>


<%
	long startTime = System.nanoTime();
	List<User> users = PersistenceUtil.findAllUsers();
%>
<div class="col-md-offset-2 col-md-7">
	<h3 class="text-center">
		All Users <br /></h3>
	<table class=" table table-striped table-bordered">
		<tr>
			<th class="text-center">Username</th>
			<th class="text-center">User Type</th>
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
			String timeTaken = String.format("<p>Query executed in %.2f ms<p>",
					(double) timeTakenInNanos / 1000000);
		%>

	</table>
	<h3 class="text-center"><%=timeTaken%></h3>
</div>
<jsp:include page="../templates/footer.jsp" />