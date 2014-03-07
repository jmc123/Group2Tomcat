function validatePasswordsMatch(){
	var y = document.forms["register"]["password"].value;
	var z = document.forms["register"]["confirm"].value;
	
	if(y != z || z == null || z == ""){
		alert("Passwords must match!");
		document.forms["register"]["password"].focus();
		return false;
	}
	return true;
}