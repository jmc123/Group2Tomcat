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
	
	var RE = /^-{0,1}\d*\.{0,1}\d+$/;
	if(!RE.test(v)){
    	alert("Invalid phone number!");
    	document.forms["register"]["phone"].focus();
    	return false;
    }
	if(v.length < 7 || v.length > 10) { 
		alert("Invalid phone number!");
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

function validateIMSI(){
	var imsi = document.forms["imsiform"]["imsi"].value;
	var RE = /^-{0,1}\d*\.{0,1}\d+$/;
    if(!RE.test(imsi)){
    	alert("Invalid IMSI format!");
    	document.forms["imsiform"]["imsi"].focus();
    	return false;
    }
    return true;
}