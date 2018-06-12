function getEmployeeDetails(){
   var employeeId = document.getElementById("employeeid").value;
   var url = "/empDetails/" + employeeId;
   window.location.replace(url);
   
   //var header = $("meta[name='_csrf_header']").attr("content");	
   /*$.ajax({ 
       type: "GET",
       url: "https://zuul-service-dev.cfapps.io/dashboard-feign/" + employeeId,
       success: function(data){        
        //  alert(data);
       }
   });*/
}


/*function getAuthToken(){
	   //var employeeId = document.getElementById("employeeid").value;
	   var contentType = $("meta[name='Content-Type']").attr("application/x-www-form-urlencoded");	
	   var authorization = $("meta[name='Authorization']").attr("Basic ZW1wbG95ZWVhcHA6cGFzcw==");	
	   $.ajax({ 
	       type: "POST",
	       dataType: "json",
	       url: "https://auth-service-dev.cfapps.io/service/oauth/token",
	       data: {"client_id": "employeeapp", "username": "user1","password": "password1","grant_type": "password"},
	       beforeSend: function(xhr) {
		       xhr.setRequestHeader(contentType, authorization);
		   },
	       success: function(data){        
	          alert(data);
	       }
	   });
	}*/


