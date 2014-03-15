<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<%
	HttpSession sess = request.getSession(false);
	String userId;
	String userName="";
	String password;
	String userType="";

	if (sess != null && !sess.isNew()) {
		userId = (String.valueOf(sess.getAttribute("id")));
		if (userId == null || userId.equals("")
				|| userId.equals("null")) {
			userName = String.valueOf(sess.getAttribute("userName"));
			password = String.valueOf(sess.getAttribute("password"));
			userType = String.valueOf(sess.getAttribute("userType"));

			if (userName == null || userName.equals("") || userName.equals("null")
					|| password == null || password.equals("")|| password.equals("null")) {
				response.sendRedirect("/JPASprint1");
			} else {
				sess.setAttribute("id", userId);
				sess.setAttribute("password", password);
			}

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

<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURANCES_BY_MODEL%></h4>
	<br />
	<br />
	<br />
	<form method="get"
		action="/JPASprint1/webpages/networkManEng/nmeCountEventCauseModel.jsp"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" id="query" name="query"
					value="CountEventCauseByModel" style="display: none" /> <input
					type="text" class="form-control" id="model" name="model"
					placeholder="<%=Strings.PH_PHONE_MODEL%>" title="<%=Strings.TT_PHONE_MODEL%>">
			</div>
		</div>
				<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>
	</form>
	<%
		if (request.getParameter("model") != null) {
			long startTime = System.nanoTime();
			String model = request.getParameter("model");

			List<Object[]> queryResults = PersistenceUtil.findUniqueEventCauseAndOccurancesByModel(model);
	%>
	<div class="col-md-offset-2 col-md-7">
		<h4 class="text-center"><%=Strings.RESULT_PHONE_MODEL%><strong> <%=model%></strong></h4>
		<div style="max-height:400px; overflow:auto;">
		<table class=" table table-striped table-bordered">
			<tr>
				<th class="text-center"><%=Strings.EVENT_ID%></th>
				<th class="text-center"><%=Strings.CAUSE_CODE%></th>
				<th class="text-center"><%=Strings.OCCURANCES%></th>
			</tr>
			<%
				for (Object[] object : queryResults) {
			%>
			<tr>
				<td class="text-center"><%=((EventCause) object[0]).getEventId()%></td>
				<td class="text-center"><%=((EventCause) object[0]).getCauseCode()%></td>
				<td class="text-center"><%=new DecimalFormat("#,###,###").format(object[1])%></td>
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
		</div>
		<h4 class="text-center"><%=timeTaken%></h4>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="../templates/footer.jsp" />