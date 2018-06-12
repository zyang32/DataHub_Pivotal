<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
    <th scope="col">Id</th>
    <th scope="col">Name</th>
    <th scope="col">Role</th>
    <th scope="col">Manage</th>
    <th scope="col"> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${emp}" >
    <tr>
    	<th scope="row">${employee.id}</th>
        <td>${employee.name}</td>
        <td>${employee.role}</td>
        <td>${employee.manager}</td>
    </tr>
    </c:forEach>
    </tbody>
  </table>


</body>
</html>