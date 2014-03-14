<%@ page import="main.*"%>
 <%@ page import ="java.util.*" %>
 <%@ page import ="entity.*" %>
 <%@ page import ="persistence.*" %>
<jsp:include page="../templates/header.jsp" />
<div class="row">
				<div class="col-md-3 text-left panel-group" id="accordion">
					<ul class="nav nav-pills nav-stacked">
						<li><a href="csHome.jsp"><span class="glyphicon glyphicon-home"></span>  <%=Strings.HOME%></a></li>
						<li><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-stats"></span><%=Strings.QUERIES%></a></li>
						
						<div id="collapseOne" class="panel-collapse collapse">
							<ul class="nav nav-pills nav-stacked text-left">
							<li><a href="csEventCause.jsp"><span class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI%></a></li>
							<li><a href="csNumOfFail.jsp"><span class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD%></a></li>
							<li><a href="csUniqueCodes.jsp"><span class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></a></li>
							</ul>
						</div>
					</ul>
					<a class="twitter-timeline" width="250" height "250" data-dnt="true" href="https://twitter.com/ericsson" data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
				</div>

<!-- content here -->
<%
	String userName = null;
	Cookie [] cookies = request.getCookies();
	for(Cookie cookie : cookies){
		userName = cookie.getValue();
	}
	User userDetails = PersistenceUtil.findUserByUsername(userName);
	String userType = userDetails.getUserType().getDesc();
	String fname = userDetails.getFirstName();
	String lname = userDetails.getLastName();
	String email = userDetails.getEmailAddress();
	String phoneNum = userDetails.getPhoneNumber();
	%>

				<div class="col-md-9">
					<h3 class="col-md-12 text-center"><strong><em><%=Strings.PROFILE_PAGE%></em></strong></h3>
					<p style="padding:1px;" class="col-md-12"></p>
					<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt><%=Strings.PROFILE_USERNAME%></dt>
						<dd style="padding-bottom:20px;"><%= userName %></dd>
						<dt><%=Strings.PROFILE_ROLE%></dt>
						<dd><%= userType %></dd>
						
					</dl>
						<div class="col-md-offset-2 col-md-10 center">
						<script src="//platform.linkedin.com/in.js" type="text/javascript"></script>
						<script type="IN/JYMBII" data-companyid="1060" data-format="inline"></script>	
						</div>				
					</div>
					<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt><%=Strings.PROFILE_FIRSTNAME%></dt>
						<dd style="padding-bottom:20px;"><%= fname %></dd>
						<dt><%=Strings.PROFILE_LASTNAME%></dt>
						<dd><%= lname %></dd>
					</dl>
					<h4 class="center col-md-offset-3" style="padding-top:20px; padding-bottom:20px;"><strong><em>Contact Details</em></strong></h4>
					<dl class="dl-horizontal">
						<dt><%=Strings.PROFILE_EMAIL%></dt>
						<dd style="padding-bottom:20px;"><%= email %></dd>
						<dt><%=Strings.PROFILE_PHONE%></dt>
						<dd><%= phoneNum %></dd>
					</dl>
					</div>
				</div>
				
<jsp:include page="../templates/footer.jsp" />