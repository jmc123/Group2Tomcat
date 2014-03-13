<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<%@ page import="entity.*"%>
<%@ page import="persistence.*"%>
<jsp:include page="../templates/header.jsp" />
<div class="row">
	<div class="col-md-3 text-left panel-group" id="accordion">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="seHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne"><span class="glyphicon glyphicon-stats"></span>
					<%=Strings.QUERIES%></a></li>

			<div id="collapseOne" class="panel-collapse collapse">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="seListIMSI.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.ALL_IMSIS_BY_TIME_PERIOD%></a></li>
					<li><a href="seCountForModel.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD%></a></li>
				</ul>
			</div>
		</ul>
		<a class="twitter-timeline" width="250" height "250" data-dnt="true" href="https://twitter.com/ericsson" data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
		
	</div>


<!-- content here -->
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		userName = cookie.getValue();
	}
	List<Object[]> userDetails = PersistenceUtil
			.findPasswordAndUserTypeByUsername(userName);
	String userType = ((UserType) userDetails.get(0)[1]).getDesc();
%>

<div class="col-md-9 text-center">
	<h3 class="col-md-10 text-center"><%=Strings.PROFILE_PAGE%></h3>
	<p style="padding: 10px;" class="col-md-12"></p>
	<dl class="dl-horizontal col-md-12">
		<dt><%=Strings.PROFILE_USERNAME%></dt>
		<dd style="padding-bottom: 20px;"><%=userName%></dd>
		<dt><%=Strings.PROFILE_ROLE%></dt>
		<dd><%=userType%></dd>
	</dl>
</div>

<jsp:include page="../templates/footer.jsp" />