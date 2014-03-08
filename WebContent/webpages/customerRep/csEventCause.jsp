 <%@ page import ="java.util.*" %>
 <%@ page import ="entity.*" %>
   <%@ page import ="persistence.*" %>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-2 col-md-7 text-center">Enter an IMSI for EventID and Cause Code</h3>
					<br /><br /><br />
					<form method="get" action="/JPASprint1/webpages/customerRep/csEventCause.jsp" class="form-horizontal">
						<div class="form-group">
							<label for="imsi" class="col-md-4 control-label"></label>
							<div class="col-md-4">
								<input type="text" id="query" name="query" value="EventCauseByIMSI" style="display: none" />
								<input type="text" class="form-control" id="IMSI" name="IMSI" placeholder="IMSI value" required />
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
					if(request.getParameter("IMSI") != null){
						long startTime = System.nanoTime();
						long imsi = Long.parseLong(request.getParameter("IMSI"));
						List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);
					
					%>
					<div class="col-md-offset-2 col-md-7">
						<h3 class="text-center">The Event IDs and Cause Codes for <br />IMSI:<%= imsi %></h3>
						<table class=" table table-striped table-bordered">
							<tr>
								<th class="text-center">Event ID </th>
								<th class="text-center">Cause Code</th>
							</tr>
							<%for(EventCause eventCause : eventCauses){%>
							<tr>
								<td class="text-center"><%= eventCause.getEventId() %></td>
								<td class="text-center"><%= eventCause.getCauseCode() %></td>
							</tr>
							<%}
							long timeTakenInNanos = System.nanoTime()-startTime;
							String timeTaken = String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000);
							%>
							
						</table>
						<h3 class="text-center"><%=timeTaken %></h3>
					</div>
					<%} %>
				</div>
				
<jsp:include page="../templates/footer.jsp" />
