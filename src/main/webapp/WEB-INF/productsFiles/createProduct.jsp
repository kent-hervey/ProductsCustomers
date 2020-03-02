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
<title>Create Product</title>
</head>

<body>
	<div class="container">
		<h1>New Product</h1>
		<div class="row">
			<div class="col-50">
			
				<form:form action="/products" method="POST" modelAttribute="product">
				
				
				<form:label path="name">Name (minimum 3 characters)</form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
				
				<form:label path="modelNumber">Model (minimum 3 characters)</form:label>
				<form:errors path="modelNumber"/>
				<form:input path="modelNumber"/>			
				
				<form:label path="listPrice">List Price</form:label>
				<form:errors path="listPrice"/>
				<form:input path="listPrice"/>			
				
		
				<form:label path="description">Description (minimum 3 characters)</form:label>
				<form:errors path="description"/>
				<form:input path="description"/>
				
				<input type="submit" value="Create"/>
				
				</form:form>
			</div>
			
		</div><!-- end row -->
			
		<div class="row">
			<div class="col-25">
				<a href="/">Home</a>
			</div>
			<div class="col-25">
				<a href="/products">Products Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
		
	</div><!-- end container -->

</body>
</html>