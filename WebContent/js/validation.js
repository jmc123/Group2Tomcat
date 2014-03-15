function validateInputsAreValid(){
	var u = document.forms["register"]["email"].value;
	var v = document.forms["register"]["phone"].value;
	var x = document.forms["register"]["role"].value;
	var y = document.forms["register"]["password"].value;
	var z = document.forms["register"]["confirm"].value;
	
	var emailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	
	if(!emailReg.test(u)){
		alert("Invalid email format!");
		document.forms["register"]["email"].focus();
		return false;
	}
	
	v.replace(/[^0-9]/g, '');
	if(v.length < 7 || v.length > 10) { 
		alert("Invalid phone number length!");
        document.forms["register"]["phone"].focus();
        return false;
	} 
	
	
	if(x == 0){
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