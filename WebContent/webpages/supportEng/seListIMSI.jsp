<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-2 col-md-7 text-center"><%=Strings.ALL_IMSIS_BY_TIME_PERIOD%></h3>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/supportEng/seListIMSI.jsp"
		class="form-horizontal">
		<div class="form-group">
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="from"
					name="from" required title="<%=Strings.TT_FROM%>"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="to"
					name="to" required title="<%=Strings.TT_TO%>"/>
			</div>
		</div>

		<br />
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>
			</div>
		</div>
	</form>

	<%
		if ((request.getParameter("from") != null) && (request.getParameter("to") != null)) {
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
			List<Long> queryDetails = PersistenceUtil
					.findCallFailuresBetweenDates(fdate, tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center">
			<%=Strings.RESULT_FROM%>
			<%=fdate%>
			<%=Strings.RESULT_TO%>
			<%=tdate%></h4>
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.IMSI%></th>
			</tr>
			<%
				for (Long object : queryDetails) {
			%>
			<tr>
				<td class="text-center"><%=object%></td>

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
		<h3 class="text-center"><%=timeTaken%></h3>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="../templates/footer.jsp" />

