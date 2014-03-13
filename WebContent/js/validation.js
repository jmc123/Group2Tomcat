function validateInputsAreValid(){
	var r = document.forms["register"]["role"].value;
	var y = document.forms["register"]["password"].value;
	var z = document.forms["register"]["confirm"].value;
	
	
	if(r == 0){
		alert("Please select a user role!");
		document.forms["register"]["role"].focus();
		return false;
	}
	
	if(y != z || z == null || z == ""){
		alert("Passwords must match!");
		document.forms["register"]["password"].focus();
		return false;
	}
	return true;
}