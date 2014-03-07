<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-4 col-md-7 text-left">Import Dataset</h3>
	<br />
	<br />
	<br />

	<form name="upload" method="post" action="/JPASprint1/ImportServlet"
		enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="file" value="Import" name="ImportFile"
					accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" required/>
			</div>
		</div>
		<br />
		<br />
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<button type="submit" class="btn btn-primary">Upload dataset</button>
			</div>
		</div>


	</form>

</div>

<jsp:include page="../templates/footer.jsp" />



