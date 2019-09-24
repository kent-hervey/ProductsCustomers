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
<title>Page Title</title>







</head>

<body>
	<div class="container">
	<h1>Products for:  ${vendor.companyName }</h1>
	<h2>${vendor.companyLocation}</h2>

	<table class="tablestyle">
		<thead>
			<tr>
				<td>Product ID, Name</td>
				<td>Model Number</td>
				<td># Customers</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.getId()}, <a href="/products/${product.getId()}">${product.getName()}</a>
					<td>${product.getModelNumber()}</td>
					<td>${product.getCustomers().size()}</td>
					<td>
						<form:form action="/products/${product.getId()}" method="POST">
							<input type="hidden" name="_method" value="delete">
							<input class="normal-link" type="submit" value="Delete">
						</form:form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="/products/new"><button>Create a New Product</button></a>
	
	
	
	
	</div>
	
	
	
	</div>



</body>
</html>