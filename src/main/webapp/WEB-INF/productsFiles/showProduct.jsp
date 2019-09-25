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
<title>Show product</title>
</head>

<body>
	<div class="container">
		<h1>Details of product:   </h1>
		<div class="col-5">
		
		
		</div>
		<div class="col-50">
			<ul>
			  <li>Name:  ${product.getName()}</li>
			  <li>Model:  ${product.getModelNumber()}</li>
			  <li>List Price:  ${product.getListPrice()}</li>
			  <li>Description:  ${product.getDescription()}</li>
			  <li>ID:  ${product.getId()}</li>
			</ul>
		</div>
		
		<table class="tablestyle">
			<thead>
				<tr>
					<td>Customer Name</td>
					<td>Customer Location</td>
					<td>Serial Number</td>
					<td>Purchase Date</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${product.getCustomers()}" var="customer">
					<tr>
						<td><a href="${customer.getId()}">${customer.getName()} ${customer.getId()}</a></td>
						<td>${customer.getLocation()}</td>
						<c:forEach items="${productCustomers}" var="productOwners">
							<c:choose>
							<c:when test="${productOwners.getProduct().getId()==product.getId() && productOwners.getCustomer().getId()==customer.getId()}">
								<td>${productOwners.getSerialNumber()}</td>
								<td>
									<fmt:formatDate type="date" value="${productOwners.getPurchaseDate()}"/>
								</td>
								<td>
									<form:form action="/products/${product.getId()}/customers/${customer.getId()}" method="POST">
										<input type="hidden" name="_method" value="delete">
										<input class="normal-link" type="submit" value="Delete">
								
								
									</form:form>
								</td>
							</c:when>
							</c:choose>
						</c:forEach>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<div class="row">
			<div class="col-25">
				<a href="/products/${product.getId()}/edit"><button>Edit Product</button></a>
			
			</div>
			
			
			<!--  
			<div class="col-25">
				<form:form action="/products/${product.getId()}" method="POST">
					<input type="hidden" name="_method" value="delete">
					<input type="submit" value="Delete Product">
				</form:form>
			</div>
			-->
		</div>
		
		<form:form action="/products/${product.getId()}/customers" method="POST" modelAttribute="productCustomer">
			<fieldset>
				<legend>Add another Customer</legend>
				<div class="row">
					<div class="col-50">
						<label for=>Customer:</label>
					</div>
					<div class="col-25">
						<select name="customer">
							<c:forEach items="${customersWithoutProduct}" var="customerNot">
								<option value="<c:out value="${customerNot.id}"/>"><c:out value="${customerNot.name}"/></option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="row">
					<div class="col-50">
						<form:label path="purchaseDate">Purchase Date:</form:label>
					</div>
					<div class="col-25">
						<form:errors path="purchaseDate"/>
						<form:input type="date" path="purchaseDate"/>

					</div>
				</div>
				
				<div class="row">
					<div class="col-50">
						<form:label path="serialNumber">Serial Number:</form:label>
					</div>
					<div class="col-25">
						<form:errors path="serialNumber"/>
						<form:input path="serialNumber"/>

					</div>
				</div>
				
				
			
			
			
				<input type="submit" value="Add">
			</fieldset>
		</form:form>
	
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