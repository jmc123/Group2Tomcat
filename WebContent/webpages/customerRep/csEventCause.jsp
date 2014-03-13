<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class=" col-md-12 text-center"><%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI%></h4>
	<br />
	<br />
	<br />
	<form method="get"
		action="/JPASprint1/webpages/customerRep/csEventCause.jsp"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" id="query" name="query" value="EventCauseByIMSI"
					style="display: none" /> <input type="text" class="form-control"
					id="IMSI" name="IMSI" placeholder="<%=Strings.PH_IMSI%>" title="<%=Strings.TT_IMSI%>" required />
			</div>
			</div>
				<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>


	</form>
	<%
		if (request.getParameter("IMSI") != null) {
			long startTime = System.nanoTime();
			long imsi = Long.parseLong(request.getParameter("IMSI"));
			List<EventCause> eventCauses = PersistenceUtil
					.findEventCauseByIMSI(imsi);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center"><%=Strings.RESULT_IMSI%><%=imsi%></h4>
		<div style="max-height:200px; overflow:auto;">
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.EVENT_ID%></th>
				<th class="text-center"><%=Strings.CAUSE_CODE%></th>
			</tr>
			<%
				for (EventCause eventCause : eventCauses) {
			%>
			<tr>
				<td class="text-center"><%=eventCause.getEventId()%></td>
				<td class="text-center"><%=eventCause.getCauseCode()%></td>
			</tr>
			<%
				}
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
