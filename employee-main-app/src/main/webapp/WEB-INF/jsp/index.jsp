<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

	<c:url value="/css/home.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	<nav class="navbar navbar-light justify-content-between" style="background-color: #e3f2fd;">
  <a class="navbar-brand">Employee Inquiry</a>
  <div class="form-inline">
    <input class="form-control mr-sm-2" id="employeeid" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" onclick="getEmployeeDetails()">Go</button>
  </div>
</nav>

	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/employeeDetails.js"></script>
</body>
</html>
