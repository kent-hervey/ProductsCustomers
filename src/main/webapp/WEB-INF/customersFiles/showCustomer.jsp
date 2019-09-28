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
<title>Show Product</title>
</head>

<body>
	<div class="container">
		<h1>Details of Customer:   </h1>
		<div class="col-5">
		
		</div>
		<div class="col-50">
			<ul>
			  <li>Name:  ${customer.getName()}</li>
			  <li>Contact Name:  ${customer.getContactName()}</li>
			  <li>Contact Email: ${customer.getContactEmail()}</li>
			  <li>Location:  ${customer.getLocation()}</li>
			  <li>ID:  ${customer.getId()}</li>
			  <li>Number of Products:  ${customer.getProducts().size()}</li>
			</ul>
		</div>
		
		<table class="tablestyle">
			<thead>
				<tr>
					<td>Product Name</td>
					<td>Product Model</td>
					<td>Serial Number</td>
					<td>Purchase Date</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customer.getProducts()}" var="product">
					<tr>
						<td><a href="/products/${product.getId()}">${product.getName()} ${product.getId()}</a></td>
						<td>${product.getModelNumber()}</td>
						<c:forEach items="${productCustomers}" var="productOwners">
							<c:choose>
							<c:when test="${productOwners.getProduct().getId()==product.getId() && productOwners.getCustomer().getId()==customer.getId()}">
								<td>${productOwners.getSerialNumber()}</td>
								<td>
									<fmt:formatDate type="date" value="${productOwners.getPurchaseDate()}"/>
								</td>
								<td>
									<form:form action="/customers/${customer.getId()}/products/${product.getId()}" method="POST">
										<input type="hidden" name="_method" value="delete">
										<input class="normal-link" type="submit" value="Delete">
									</form:form>
								</td>
							</c:when>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="row">
			<div class="col-25">
				<a href="/customers/${customer.getId()}/edit"><button>Edit Customer</button></a>
			</div>
			<!--  
			<div class="col-25">
				<form:form action="/customers/${customer.getId()}" method="POST">
					<input type="hidden" name="_method" value="delete">
					<input type="submit" value="Delete Product">
				</form:form>
			</div>
			-->
		</div>
		
		<form:form action="/customers/${customer.getId()}/products" method="POST" modelAttribute="productCustomer">
			<fieldset>
				<legend>Add another Product to this Customer</legend>
				<div class="row">
					<div class="col-50">
						<label for=>Product:</label>
					</div>
					<div class="col-25">
						<select name="product">
							<c:forEach items="${productsWithoutCustomer}" var="productNot">
								<option value="<c:out value="${productNot.id}"/>"><c:out value="${productNot.name}"/></option>
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
				<a href="/customers">Customers Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div><!-- end container -->


</body>
</html>