<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-11 text-center"><%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></h4>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/customerRep/csUniqueCodes.jsp"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="IMSI" name="IMSI"
					placeholder="<%=Strings.PH_IMSI%>" required title="<%=Strings.TT_IMSI%>"/>
			</div>
		</div>

		<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>
	</form>

	<%
		if (request.getParameter("IMSI") != null) {
			long startTime = System.nanoTime();
			long imsi = Long.parseLong(request.getParameter("IMSI"));

			List<Integer> uniqueCauseCodes = PersistenceUtil
					.findUniqueCauseByIMSI(imsi);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center"><%=Strings.RESULT_IMSI%><%=imsi%></h4>
		<div style="max-height:250px; overflow:auto;">
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.CAUSE_CODE%></th>
			</tr>
			<%
				for (Integer causeCode : uniqueCauseCodes) {
			%>
			<tr>
				<td class="text-center"><%=causeCode%></td>
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
		<h4 class="text-center"><%=timeTaken%></h4>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="../templates/footer.jsp" />