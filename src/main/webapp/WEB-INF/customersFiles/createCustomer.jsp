<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/styles.css"/>
<meta charset="ISO-8859-1">
<title>Create Customer</title>
</head>

<body>
	<div class="container">
		<h1>New Customer</h1>
		<div class="row">
			<div class="col-50">
			
				<form:form action="/customers" method="POST" modelAttribute="customer">
				
				
				<form:label path="name">Name</form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
				
				<form:label path="ContactName">Contact Name</form:label>
				<form:errors path="ContactName"/>
				<form:input path="ContactName"/>			
				
				<form:label path="contactEmail">Contact Email</form:label>
				<form:errors path="contactEmail"/>
				<form:input path="contactEmail"/>	
				
				<form:label path="location">Location</form:label>
				<form:errors path="location"/>
				<form:input path="location"/>		
				

				
				<input type="submit" value="Create"/>
				
				</form:form>
			</div>
			
		</div><!-- end row -->
			
		<div class="row">
			<div class="col-25">
				<a href="/">Home</a>
			</div>
			<div class="col-25">
				<a href="/customers">Customers Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
		
	</div><!-- end container -->

</body>
</html>