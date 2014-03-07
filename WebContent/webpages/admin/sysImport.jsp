<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

				<div class="col-md-9 text-center">
					<h3 class="col-md-offset-4 col-md-7 text-left">Import Dataset</h3>
					<br /><br /><br />
					
					<form name="upload" method="post" action="/JPASprint1/ImportServlet" enctype="multipart/form-data">

						<input type="file" value="Import" name="ImportFile"
								accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
						<input type="submit" value="Submit" />
					</form>
					
				</div>
				
<jsp:include page="../templates/footer.jsp" />