# Products and Customers

Products and Customers is a simple web application that manages the products a vendor or distributor has under use by customers, and also manages the customers using those products

# Installation
This is a Maven/Spring Boot project with default WAR settings

Spring Boot will populate your MySQL file.  Just create the file and if different, change in the application.properties file


[comment]: # (mvn spring-boot:run)


# Usage

Vendors/distributors can track their customers by product, and product by customers.  

To start, in the Product side, add the various products that are in use.  Fields are:  
  * Name
  * Model
  * List Price
  * Description

Next, on the Customer side, add the various customers.  Fields are:
  * Name (Corporate)
  * Contact Name
  * Contact Email
  * Location

Next, the relationship between customers and products can be populated on either side.  This shows which customers hava a product and which products are in use by a customer.  These fields are:
  * Purchase Date
  * Serial Number
Note:  this means that adding or deleting a product to/from a customer also means that customer was added or deleted to/from that product


## API Documentation
Note:  all use content-type:  application/json & Media-type:  JSON

RU for Vendor...this is the entity that owns the products and the customers being tracked:
* Fetch  Vendor info
  *   GET  api/vendor  Note;  did not specify vendor as there is always only one
* Update Vendor info
  *   PUT api/vendor  


CRUD for Customers:
* Add a customer
  *  Post api/customers
* Fetch all customers
  * Get  api/customers
* Fetch all customers of product {id} showing only customers
  * GET api/customers/products/{id}
Fetch all products for customer 3
GET api/customers/4/products
Fetch customer 1
Get api/customers/1
Update customer 1
PUT   api/customers/1
Delete customer 21
Delete  api/customers/21
CRUD for middle/Join table of Customers-Products
Add product 3 to customer 1, Note:  also adds customer 1 to product 3
Post  /api/customers/1/products/3
OR
pending Post /api/products/3/customers/1
Delete product 3 from customer 1
Delete api/customers/4/products/1
OR
Delete api/products/1/customers/4
CRUD for Products
Add a product
Post api/products
Fetch all products
GET api/products
Fetch all products of customer 3 showing only products
GET api/products/customers/3
Fetch all customers for product 3 with product showing
GET api/products/3/customers 
Fetch product,  #1
GET api/products/1
Update product 1
PUT api/products/1
Delete a product
PUT api/products/22
Other
Does Customer have this product/check to see if product 3 to customer 1 exists
Get api/customers/1/products/3
Fetch number of customers
GET api/customers-customers-number-of
Fetch list of customer IDs
GET api/customer-ids
Check validity of customer id; returns true or false
Get api/customers-id/15
Fetch number of products
Get api/proudcts-number-of
Fetch list of product IDs
Get api/product-ids
Check validity of product id; returns true or false
Get api/products-id/1




## Selected JSP Screenshots:

![Image](readmeimages/home.png "Home Page")  
![Image](readmeimages/customers.png "Customers Overview")  
![Image](readmeimages/products.png "Products Overview")  
![Image](readmeimages/customer.png "Detail One Customer")  
![Image](readmeimages/product.png "Detail One Product")  
![Image](readmeimages/newproduct.png "Create New Product")  





# Potential Future Features:

  * Added form input flexibility
  * Search, sort, or pagination to accommodate long lists







