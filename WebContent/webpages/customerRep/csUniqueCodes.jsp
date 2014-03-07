<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-2 col-md-7 text-center">Enter an IMSI for all Unique Cause Codes for it's Call Failures</h3>
					<br /><br /><br />
					<form method="get" action="/JPASprint1/CSRepServlet" class="form-horizontal">
						<div class="form-group">
							<label for="imsi" class="col-md-4 control-label">IMSI:</label>
							<div class="col-md-4">
								<input type="text" id="query" name="query" value="UniqueCauseCodesByIMSI" style="display: none" />
								<input type="text" class="form-control" id="IMSI" name="IMSI">
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