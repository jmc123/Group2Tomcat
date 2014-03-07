<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-2 col-md-7 text-center">See all the IMSI's with call failures</h3>
					<br /><br /><br />
					<form method="get" action="/JPASprint1/SEngServlet" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="from" class="col-md-4 control-label">FROM:</label>
							<div class="col-md-4">
								<input type="text" id="query" name="query" value="AllIMSITimePeriod" style="display: none" />
								<input type="datetime-local" class="form-control" id="from" name="imsifrom">
							</div>
						</div>
						<div class="form-group">
							<label for="to" class="col-md-4 control-label">TO:</label>
							<div class="col-md-4">
								<input type="datetime-local" class="form-control" id="to" name="imsito">
							</div>
						</div>

						<br />
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
				</div>
				
<jsp:include page="../templates/footer.jsp" />