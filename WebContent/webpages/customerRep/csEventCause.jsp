<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-2 col-md-7 text-center">Enter an IMSI for EventID and Cause Code</h3>
					<br /><br /><br />
					<form method="get" action="/JPASprint1/IMSIServlet" class="form-horizontal">
						<div class="form-group">
							<label for="imsi" class="col-md-4 control-label"></label>
							<div class="col-md-4">
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
				</div>
				
<jsp:include page="../templates/footer.jsp" />