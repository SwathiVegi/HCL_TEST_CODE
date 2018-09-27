<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<title>Employee Registration Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Registration Form</h2>
 
	<form:form method="POST" modelAttribute="employee">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="name">First Name: </label> </td>
				<td><form:input path="firstName" id="firstName"/></td>
				<td><form:errors path="firstName" cssClass="error"/></td>
		    </tr>
		    <tr>
				<td><label for="name">Last Name: </label> </td>
				<td><form:input path="lastName" id="lastName"/></td>
				<td><form:errors path="lastName" cssClass="error"/></td>
		    </tr>
	    
	    
			<tr>
				<td><label for="joiningDate">Joining Date(dd/MM/yyyy): </label> </td>
				<td><form:input path="enteringDate" id="joiningDate"/></td>
				<td><form:errors path="enteringDate" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="nationality">Nationality: </label> </td>
				<td><form:input path="nationality" id="nationality"/></td>
				<td><form:errors path="nationality" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="code">Employee Number: </label> </td>
				<td><form:input path="code" id="code"/></td>
				<td><form:errors path="code" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>