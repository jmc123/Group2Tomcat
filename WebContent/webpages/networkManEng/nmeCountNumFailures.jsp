<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-2 col-md-7 text-center">Enter an IMSI and
		time period for number of failures and their duration</h3>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/networkManEng/nmeCountNumFailures.jsp"
		class="form-horizontal">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="imsi" name="imsi"
					placeholder="IMSI" required />
			</div>
		</div>
		<div class="form-group">
			<label for="from" class="col-md-4 control-label">FROM:</label>
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="from"
					name="imsifrom">
			</div>
		</div>
		<div class="form-group">
			<label for="to" class="col-md-4 control-label">TO:</label>
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="to"
					name="imsito">
			</div>
		</div>

		<br />
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form>
	<%
		if (request.getParameter("imsi") != null) {
			long startTime = System.nanoTime();
			long imsi = Long.parseLong(request.getParameter("imsi"));
			String fromdate = PersistenceUtil.returnDate(request
					.getParameter("imsifrom"));
			String todate = PersistenceUtil.returnDate(request
					.getParameter("imsito"));
			SimpleDateFormat currentParsed = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date fdate = null, tdate = null;
			try {
				fdate = currentParsed.parse(fromdate);
				tdate = currentParsed.parse(todate);
			} catch (ParseException e) {

			}
			List<Object[]> queryResults = PersistenceUtil
					.findNumberOfFailuresAndDuration(imsi, fdate, tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h3 class="text-center">
			The Number of Failures and Duration for <br />IMSI:<%=imsi%>
			from <br />
			<%=fdate%>
			to:
			<%=tdate%></h3>
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center">Number Of Failures</th>
				<th class="text-center">Duration in Milliseconds</th>
			</tr>
			<%
				for (Object[] object : queryResults) {
			%>
			<tr>
				<td class="text-center"><%=object[0]%></td>
				<td class="text-center"><%=object[1]%></td>
			</tr>
			<%
				}
			%>
			<%
				long timeTakenInNanos = System.nanoTime() - startTime;
					String timeTaken = String.format(
							"<p>Query executed in %.2f ms<p>",
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