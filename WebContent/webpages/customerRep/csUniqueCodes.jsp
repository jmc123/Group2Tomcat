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
	<h4 class="col-md-11 text-center"><%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></h4>
	<br /> <br /> <br />
	<form name="imsiform" id="imsiform" method="get"
		action="/JPASprint1/webpages/customerRep/csUniqueCodes.jsp"
		onsubmit="return validateIMSI()" class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="imsi" name="imsi"
					placeholder="<%=Strings.PH_IMSI%>" required
					title="<%=Strings.TT_IMSI%>" />
			</div>
		</div>

		<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>
	</form>

	<%
		if (request.getParameter("imsi") != null) {
			long startTime = System.nanoTime();
			long imsi = Long.parseLong(request.getParameter("imsi"));

			if (PersistenceUtil.isIMSIValid(imsi)) {

				List<Integer> uniqueCauseCodes = PersistenceUtil
						.findUniqueCauseByIMSI(imsi);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center"><%=Strings.RESULT_IMSI%><strong>
				<%=imsi%></strong>
		</h4>
		<div style="max-height: 400px; overflow: auto;">
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
		} else {
														//Alert for invalid IMSI
			}
		}
	%>
</div>

<jsp:include page="../templates/footer.jsp" />