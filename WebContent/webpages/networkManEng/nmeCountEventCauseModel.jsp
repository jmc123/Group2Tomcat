<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-2 col-md-7 text-center">Enter a phone
		model for unique EventCauses and Occurances</h3>
	<br />
	<br />
	<br />
	<form method="get"
		action="/JPASprint1/webpages/networkManEng/nmeCountEventCauseModel.jsp"
		class="form-horizontal">
		<div class="form-group">
			<label for="imsi" class="col-md-4 control-label">Model:</label>
			<div class="col-md-4">
				<input type="text" id="query" name="query"
					value="CountEventCauseByModel" style="display: none" /> <input
					type="text" class="form-control" id="model" name="model">
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

			List<Object[]> queryResults = PersistenceUtil
					.findUniqueEventCauseAndOccurancesByModel(model);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h3 class="text-center">
			The EventID/CauseCode combinations and occurances for <br />Model:
			<%=model%></h3>
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center">Event ID</th>
				<th class="text-center">Cause Code</th>
				<th class="text-center">Occurances</th>
			</tr>
			<%
				for (Object[] object : queryResults) {
			%>
			<tr>
				<td class="text-center"><%=((EventCause) object[0]).getEventId()%></td>
				<td class="text-center"><%=((EventCause) object[0]).getCauseCode()%></td>
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