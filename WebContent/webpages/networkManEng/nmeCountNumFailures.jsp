<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
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
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-offset-2 col-md-7 text-center"><%=Strings.NUM_FAILURES_FOR_EACH_IMSI_BY_TIME_PERIOD%></h4>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/networkManEng/nmeCountNumFailures.jsp"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" title="<%=Strings.TT_FROM%>" data-toggle="tooltip" data-placement="bottom" value="2013-01-01T12:00:00" step="1">
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to"
					name="to" title="<%=Strings.TT_TO%>" data-toggle="tooltip" data-placement="bottom" value="2013-12-31T23:59:00" step="1">
			</div>
		</div>
				<span style="display:inline"><button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button></span>

	</form>
	<%
		if (request.getParameter("to") != null && request.getParameter("from") != null) {
			long startTime = System.nanoTime();
			String fromdate = PersistenceUtil.returnDate(request
					.getParameter("from"));
			String todate = PersistenceUtil.returnDate(request
					.getParameter("to"));
			SimpleDateFormat currentParsed = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date fdate = null, tdate = null;
			try {
				fdate = currentParsed.parse(fromdate);
				tdate = currentParsed.parse(todate);
			} catch (ParseException e) {

			}
			List<Object[]> queryResults = PersistenceUtil.findNumberOfFailuresAndDuration(fdate, tdate);
			String fDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fdate);
			String tDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
	<h4 class="text-center"><%=Strings.DATE_RANGE%><strong><%=fDateOut%> - <%=tDateOut%></strong></h4>
	<div style="max-height:400px; overflow:auto;">
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.IMSI%></th>
				<th class="text-center"><%=Strings.NUM_FAILURES%></th>
				<th class="text-center"><%=Strings.DURATION%></th>
			</tr>
			<%
				for (Object[] object : queryResults) {
			%>
			<tr>
				<td class="text-center"><%=object[0]%></td>
				<td class="text-center"><%=new DecimalFormat("#,###,###").format(object[1])%></td>
				<td class="text-center"><%=new DecimalFormat("#,###,###").format(object[2])%></td>
			</tr>
			<%
				}
			%>
			<%
				long timeTakenInNanos = System.nanoTime() - startTime;
					String timeTaken = String.format(
							"<p>" + Strings.QUERY_EXECUTION_TIME + "<p>",
							(double) timeTakenInNanos / 1000000);
			%>

		</table>
		</div>
		<h4 class="text-center"><%=timeTaken%></h4>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="../templates/footer.jsp" />