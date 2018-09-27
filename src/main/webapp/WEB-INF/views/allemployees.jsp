<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<style type="text/css">
body {
    background-color: white;
    font-family:Courier; 
    color:grey; 
    font-size: 20px;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Employees</title>

</head>


<body>

	<form:form method="GET" modelAttribute="employee">
		<table style="width:20%">
			<tr>
				<td>Search:</td>
				<td><input type="text" name="keyword" value="${keyword}" /></td>
				<td><input type="submit" name="search" value="search" /></td>
			</tr>
		</table>

	</form:form>

	<table>
		<tr>
			<c:if test="${not empty keyword}">
				<td>
					<p>
						You have searched for: ${keyword}. <a
							href="<c:url value='/list' />">Show All Employees</a>
					</p>
				</td>
			</c:if>
		</tr>
	</table>


	<c:if test="${not empty employees}">
		<h2>List of Employees</h2>
		<table>
			<tr style="font-weight: bold; background-color: #C6C9C4;">
				<th>First Name</th>
				<th>Last Name</th>
				<th>Joining Date(yyyy-mm-dd)</th>
				<th>Nationality</th>
				<th>Employee Number</th>
				<th></th>
			</tr>
			<c:forEach items="${employees}" var="emp">
				<tr>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.enteringDate}</td>
					<td>${emp.nationality}</td>
					<td><a href="<c:url value='/edit-${emp.code}-employee' />">${emp.code}</a></td>
					<td><a href="<c:url value='/delete-${emp.code}-employee' />">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty employees}">
		<h3>Looks like there are no Employee records available for your search criteria.</h3>
		<br />
		<br />
	</c:if>


	<br />
	<a href="<c:url value='/new' />">Add New Employee</a>
</body>
</html>