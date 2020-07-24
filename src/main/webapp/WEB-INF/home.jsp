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
<title>Customers Products</title>
<!-- version 0.5 api additions -->
</head>

<body>
	<div class="container">
		<h1>Welcome to Customers and Products Dashboard</h1>
		
		<h3>Below select to view by either products or customers showing all of our sold/active products and active customers and their associations.</h3>
		<div class="col-25">
		</div>
		
		<div class="col-50">
			<ul>
			  <li><a href="/customers">View By Customers</a></li>
			  <li><a href="/products">View by Products</a></li>
			  <li><a href="/api/customers">API view example of Customers</a></li>
			</ul>
		</div>
	</div>

</body>
</html>