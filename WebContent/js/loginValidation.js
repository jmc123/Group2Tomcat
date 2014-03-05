
function validateLogin()
{
var x=document.forms["loginForm"]["id"]["password"].value;
if (x==null || x=="")
  {
  alert("Please enter username and password");
  return false;
  }
}
