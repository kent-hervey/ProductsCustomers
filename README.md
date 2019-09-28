# Products and Customers

Products and Customers is a simple web application that manages the products a vendor or distributor has under use by customers, and also manages the customers using those products

# Installation
This is a Maven/Spring Boot project with default WAR settings

Spring Boot will populate your MySQL file.  Just create the file and if different than the default, productcustomer, change in the application.properties file

Final version will include a WAR export that can be run by java -jar filename.war

mvn spring-boot:run

# Usage

Vendors/distributors can track their customers by product, and product by customers.  

To start, in the Product side, add the various products that are in use.  Fields are:  
Bullet list:

  * Name
  * Model
  * List Price
  * Description

Next, on the Customer side, add the various customers.  Fields are:
Bullet list:
  * Name (Corporate)
  * Contact Name
  * Contact Email
  * Location

Next, the relationship between customers and products can be populated on either side.  This shows which customers hava a product and which products are in use by a customer.  These fields are:
Bullet list:
  * Purchase Date
  * Serial Number



# Screenshots:


![Image](readmeimages/ProductDetails.png "Product Details")




# Potential Future Features:
Bullet list:
  * API interface
  * Search, sort, or pagination to accommodate long lists







