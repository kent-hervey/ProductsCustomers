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
<title>Customers</title>

</head>

<body>
	<div class="container">
	<h1>Customers of ${vendor.companyName }</h1>
	<h2>${vendor.companyLocation}</h2>
	<p> </p>
	
	<h3>Total Customers:  ${customers.size()}</h3>

	<table class="tablestyle">
		<thead>
			<tr>
				<td>#</td>
				<td>Customer ID, Name</td>
				<td>Contact Name</td>
				<td># Products</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers}" var="customer" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td>${customer.getId()}, <a href="/customers/${customer.getId()}">${customer.getName()}</a>
					<td>${customer.getContactName()}</td>
					<td>${customer.getProducts().size()}</td>
					<td>
						<form:form action="/customers/${customer.getId()}" method="POST">
							<input type="hidden" name="_method" value="delete">
							<input class="normal-link" type="submit" value="Delete">
						</form:form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="/customers/new"><button>Create a New Customer</button></a>

	</div>
	
			<div class="row">
			<div class="col-25">
				<a href="/">Home</a>
			</div>
			<div class="col-25">
				<a href="products">Products Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div>



</body>
</html>