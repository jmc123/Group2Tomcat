<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-2 col-md-7 text-center">Enter model of
		phone for a count of call failures</h3>
	<br /> <br /> <br />
	<form method="get" action="/JPASprint1/webpages/supportEng/seCountForModel.jsp"
		class="form-horizontal">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="model" name="model"
					placeholder="Phone Model" required />
			</div>
		</div>
		<div class="form-group">
			<label for="from" class="col-md-4 control-label">FROM:</label>
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="from"
					name="from">
			</div>
		</div>
		<div class="form-group">
			<label for="to" class="col-md-4 control-label">TO:</label>
			<div class="col-md-4">
				<input type="datetime-local" class="form-control" id="to"
					name="to">
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
			List<Long> queryDetails = PersistenceUtil.findNumberOfFailuresByModelOverTime(model, fdate, tdate);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h3 class="text-center">Number of failues for <br /> Model: <%=model %> from <br />
			<%=fdate%>
			to:
			<%=tdate%></h3>
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center">Number of failures</th>

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