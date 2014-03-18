<%@ page import="main.*"%>

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
	<div class="col-md-3 text-left panel-group" id="accordion">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="nmeHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne"><span class="glyphicon glyphicon-stats"></span> <%=Strings.QUERIES%></a></li>

			<div id="collapseOne" class="panel-collapse collapse in">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="nmeCountNumFailures.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_FOR_EACH_IMSI_BY_TIME_PERIOD%></a></li>
					<li><a href="nmeCountEventCauseModel.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURANCES_BY_MODEL%></a></li>
				</ul>
			</div>
		</ul>
		<a class="twitter-timeline" width="250" height "250" data-dnt="true"
			href="https://twitter.com/ericsson"
			data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
	</div>