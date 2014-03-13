<%@ page import="main.*"%>
<div class="row">
	<div class="col-md-3 text-left panel-group" id="accordion">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="nmeHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne"><span class="glyphicon glyphicon-stats"></span>
					<%=Strings.QUERIES%></a></li>

			<div id="collapseOne" class="panel-collapse collapse in">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="nmeCountNumFailures.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_FOR_EACH_IMSI_BY_TIME_PERIOD%></a></li>
					<li><a href="nmeCountEventCauseModel.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span>
							<%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURANCES_BY_MODEL%></a></li>
				</ul>
			</div>
		</ul>
		<a class="twitter-timeline" width="250" height "250" data-dnt="true" href="https://twitter.com/ericsson" data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
	</div>