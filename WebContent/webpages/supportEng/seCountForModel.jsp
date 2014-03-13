<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-2 col-md-7 text-center"><%=Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD%></h3>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/supportEng/seCountForModel.jsp"
		class="form-horizontal">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="model" name="model"
					placeholder="Phone Model" required />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="from"
					name="from">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="to" name="to">
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
		if (request.getParameter("model") != null) {
			long startTime = System.nanoTime();
			String model = request.getParameter("model");
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
					.findNumberOfFailuresByModelOverTime(model, fdate,
							tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center"><%=Strings.RESULT_PHONE_MODEL%>
			<%=model%><br />
			<%=Strings.RESULT_FROM%>
			<%=fdate%>
			<%=Strings.RESULT_TO%>
			<%=tdate%></h4>
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.NUM_FAILURES%></th>

			</tr>
			<%
				for (Long object : queryDetails) {
			%>
			<tr>
				<td class="text-center"><%=new DecimalFormat("#,###,###").format(object)%></td>

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
		<h3 class="text-center"><%=timeTaken%></h3>
	</div>
	<%
		}
	%>

</div>

<jsp:include page="../templates/footer.jsp" />