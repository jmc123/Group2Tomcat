 <%@ page import="main.*"%>
 <%@ page import ="java.util.*" %>
 <%@ page import ="entity.*" %>
 <%@ page import ="persistence.*" %>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />

<!-- content here -->
<%
	String userName = null;
	Cookie [] cookies = request.getCookies();
	for(Cookie cookie : cookies){
		userName = cookie.getValue();
	}
	List<Object[]> userDetails = PersistenceUtil.findPasswordAndUserTypeByUsername(userName);
	String userType = ((UserType) userDetails.get(0)[1]).getDesc();
	%>

				<div class="col-md-9 text-center">
					<h3 class="col-md-10 text-center"><%=Strings.PROFILE_PAGE%></h3>
					<p style="padding:10px;" class="col-md-12"></p>
					<dl class="dl-horizontal col-md-12">
						<dt><%=Strings.PROFILE_USERNAME%></dt>
						<dd style="padding-bottom:20px;"><%= userName %></dd>
						<dt><%=Strings.PROFILE_ROLE%></dt>
						<dd><%= userType %></dd>
					</dl>
				</div>
				
<jsp:include page="../templates/footer.jsp" />