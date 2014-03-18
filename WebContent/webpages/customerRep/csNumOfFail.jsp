<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

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
		}
	}
%>
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD%></h4>
	<br /> <br /> <br />
	<form method="get"
	name="imsiform" id="imsiform" 
		action="/JPASprint1/webpages/customerRep/csNumOfFail.jsp"
		onsubmit="return validateIMSI()"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="text" class="form-control" id="imsi" name="imsi"
					placeholder="<%=Strings.PH_IMSI%>" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" value="2013-01-01T00:00:00" step="1" required
					title="<%=Strings.TT_FROM%>">
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to"
					value="2013-12-31T23:59:00" step="1" name="to" required
					title="<%=Strings.TT_TO%>">
			</div>
		</div>
		<span style="display:inline"><button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button></span>
	</form>

	<%
		if (request.getParameter("imsi") != null) {
			long startTime = System.nanoTime();
			long imsi = Long.parseLong(request.getParameter("imsi"));
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

			List<Object[]> queryDetails = PersistenceUtil
					.findNumberOfFailures(imsi, fdate, tdate);
			String fDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fdate);
			String tDateOut = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
		<div style="max-height: 250px; overflow: auto;">
			<h4 class="text-center"><%=Strings.RESULT_IMSI%><strong> <%=imsi%></strong><br />
			<%=Strings.DATE_RANGE%><strong> <%=fDateOut%> - <%=tDateOut%></strong></h4>
			<table class=" table table-striped table-bordered">
				<tr>
					<th class="text-center"><%=Strings.NUM_FAILURES%></th>
				</tr>
				<%
					for (Object[] object : queryDetails) {
				%>
				<tr>
					<td class="text-center"><%=new DecimalFormat("#,###,###").format(object[0])%></td>
				</tr>
				<%
					}
				%>
				<%
					long timeTakenInNanos = System.nanoTime() - startTime;
						String timeTaken = String.format("<p>"
								+ Strings.QUERY_EXECUTION_TIME + "<p>",
								(double) timeTakenInNanos / 1000000);
				%>

			</table>
		</div>
		<h4 class="text-center"><%=timeTaken%>
		</h4>
	</div>
	<%
		}
	%>
</div>
<jsp:include page="../templates/footer.jsp" />


