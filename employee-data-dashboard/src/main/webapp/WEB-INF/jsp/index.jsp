<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<c:url value="/css/home.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Employee Inquiry</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
			<label for="employeeid">Employee id:</label>
			<input id="employeeid" type="text" maxlength="8" size="15" required>
    		<button class="btn btn-sm btn-primary" type="button" id="employeeDetailsLink" onclick="getEmployeeDetails()"> Go </button>
    		<button class="btn btn-sm btn-primary" type="button" id="employeeDetailsLink" onclick="getAuthToken()"> Get Auth Token </button>
    		
		</div>
	</nav>

	<div class="container">
		<div class="starter-template">
			<!--  <h1>Spring Boot Web JSP Example</h1>
			<h2>Message: ${message}</h2> -->
			
		</div>
	</div>
	
	<!--  <div>
		<input id="productQuantity${product.key.id}" type="text" maxlength="7" size="7" value="${product.value}" required> </input>
    	<button class="btn btn-sm btn-primary" type="button" id="updateQuantityLink" onclick="updateCartQuantity(${product.key.id})"> Go </button>
	</div> -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/employeeDetails.js"></script>
</body>
</html>