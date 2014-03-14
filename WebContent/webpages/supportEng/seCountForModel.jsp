<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />
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
		} else if (userId.equals("4")) {
			response.sendRedirect(request.getContextPath()
					+ "/webpages/customerRep/csHome.jsp");
		}
	}
%>
<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-12 text-center"><%=Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD%></h3>
	<br /> <br /> <br />
	<form method="get"
		action="/JPASprint1/webpages/supportEng/seCountForModel.jsp"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="text" class="form-control" id="model" name="model"
					placeholder="Phone Model" required />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" value="2013-01-01T12:00:00" step="1"
					data-toggle="tooltip" data-placement="bottom" required
					title="<%=Strings.TT_FROM%>">
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to" name="to"
					value="2013-12-12T12:00:00" step="1" data-toggle="tooltip"
					data-placement="bottom" required title="<%=Strings.TT_TO%>">
			</div>
		</div>
		<button type="submit" class="btn btn-primary"><%=Strings.SUBMIT%></button>
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
		</h4>
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
		<h4 class="text-center"><%=timeTaken%></h4>
	</div>
	<%
		}
	%>

</div>

<jsp:include page="../templates/footer.jsp" />