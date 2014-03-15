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
	userName = String.valueOf(sess.getAttribute("userName"));
	userType = String.valueOf(sess.getAttribute("userType"));

	
}
%>

<div class="col-md-9 text-center">
	<h3 class="col-md-9 text-center"><em><%=Strings.PROFILE_PAGE%></em></h3>
	<p style="padding: 10px;" class="col-md-12"></p>
	<dl class="dl-horizontal col-md-12">
		<dt><%=Strings.PROFILE_USERNAME%></dt>
		<dd style="padding-bottom: 20px;"><%=userName%></dd>
		<dt><%=Strings.PROFILE_ROLE%></dt>
		<dd><%=userType%></dd>
	</dl>
</div>

<jsp:include page="../templates/footer.jsp" />