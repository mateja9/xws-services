var serverContext = "http://localhost:8080/"

document.getElementById("myButton").addEventListener("click", savePass);
var url = new URL(window.location.href);
var token = url.searchParams.get("token");
document.getElementById("token").value = token;
	
function savePass(){
    
	var formData= $('form').serialize();
	
    $.post(serverContext + "user/savePassword",formData ,function(data){
        window.location.href = serverContext + "login?message="+data.message;
    })

}