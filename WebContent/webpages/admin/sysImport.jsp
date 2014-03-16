<%@ page import="main.*"%>
<%@ page import="configs.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<%@ page import="java.text.DecimalFormat"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
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
		} else if (userId.equals("4")) {
			response.sendRedirect(request.getContextPath()
					+ "/webpages/customerRep/csHome.jsp");
		}
	}
%>
<!-- content here -->
<script type="text/javascript">

function toggle_visibility(tbid,lnkid)

{

  if(document.all){document.getElementById(tbid).style.display = document.getElementById(tbid).style.display == "block" ? "none" : "block";}

  else{document.getElementById(tbid).style.display = document.getElementById(tbid).style.display == "table" ? "none" : "table";}

  document.getElementById(lnkid).value = document.getElementById(lnkid).value == "[-] Collapse" ? "[+] Expand" : "[-] Collapse";

 }

</script>

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-4 col-md-7 text-left"><em><%=Strings.IMPORT%></em></h3>
	<br /> <br /> <br />

	<form name="upload" method="post" action="/JPASprint1/ImportServlet"
		enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="file" value="Import" name="ImportFile"
					accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					required />
			</div>
		</div>
		<br /> <br />
		<div class="form-group">
			<div class="col-md-offset-3 col-md-4">
				<button type="submit" class="btn btn-primary"><%=Strings.UPLOAD%></button>
			</div>
		</div>
	</form>

	<%
		if (DatabaseGenerator.datasetUploaded()) {
			DecimalFormat dFormatter = new DecimalFormat("#,###,###");
	%>
	<br />
	<br />
	<div style="text-align:left">
		<p>Successfully imported <strong><%=DatabaseGenerator.fileName%></strong>!</p>
		<br />
		<p><strong><%=dFormatter.format(CallFailureConfig.numberOfCallFailures())%></strong> Call Failures added to database (Total: <%=dFormatter.format(PersistenceUtil.numberOfCallFailures())%>)
		<br />
		<br />
			<strong><%=dFormatter.format(EventCauseConfig.numberOfEventCauses())%></strong> Event ID/Cause Code combinations added<br />
			<strong><%=dFormatter.format(FailureClassConfig.numberOfFailureClasses())%></strong> FailureClass IDs added<br />
			<strong><%=dFormatter.format(MCC_MNCConfig.numberOfMCC_MNCs())%></strong> Market/Operator combinations added<br />
			<strong><%=dFormatter.format(UETypeConfig.numberOfUETypes())%></strong> TAC IDs added<br />
			<strong><%=dFormatter.format(CallFailureConfig.numberOfInvalidCallFailures())%></strong> Call Failures removed due to inconsistencies<br />
		
			<div class="span4" style="max-height: 400px; overflow: auto;">
			<table class=" table table-striped table-bordered">
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Date</th>
					<th class="text-center">Event ID</th>
					<th class="text-center">Failure Class</th>
					<th class="text-center">UE Type</th>
					<th class="text-center">Market</th>
					<th class="text-center">Operator</th>
					<th class="text-center">Cell ID</th>
					<th class="text-center">Duration</th>
					<th class="text-center">Cause Code</th>
					<th class="text-center">NE Version</th>
					<th class="text-center">IMSI</th>
					<th class="text-center">HIER3_ID</th>
					<th class="text-center">HIER32_ID</th>
					<th class="text-center">HIER321_ID</th>
				</tr>
			<%
				for(DatasetEntity failure : CallFailureConfig.getInvalidFailures()){
			%>
			<tr>
				<td class="text-center"><%=((InvalidCallFailure) failure).id%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).date%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).eventId%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).failureClass%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).ueType%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).market%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).operator%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).cellId%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).duration%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).causeCode%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).neVersion%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).imsi%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).hier3_id%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).hier32_id%></td>
				<td class="text-center"><%=((InvalidCallFailure) failure).hier321_id%></td>
			</tr>
			<%	
			}
			%>
				</table>
		</div>
			
			
		<p>Uploaded in <%=String.format("%.2f", (double) DatabaseGenerator.datasetConfirmed()/1000)%> seconds</p>
		 
		<%
			}
		%>
	</div>

</div>

<jsp:include page="../templates/footer.jsp" />