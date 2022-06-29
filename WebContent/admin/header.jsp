<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<img src="../images/logo26.png" />
</div>
<div align="center">
	<div>
		Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a> <br /> <br />
	</div>
	<div id="headermenu">
		<div>
			<a href="list_users">
				<img src="../images/user2.png"/><br/>Users
			</a>
		</div>
		<div>
			<a href="list_category">
				<img src="../images/category2.png"/><br/>Categories
			</a> 
		</div> 
		<div>
			<a href="list_books">
				<img src="../images/books2.png"/><br/>Books  
			</a>
		</div> 
		<div>
			<a href="list_customer">
				<img src="../images/customer2.png"/><br/>Customers  
			</a>
		</div>
		<div>
			<a href="list_review">
			<img src="../images/rating2.png"/><br/>Reviews 
			</a>
		</div>
		<div>
			<a href="orders">
				<img src="../images/order2.png"/><br/>Orders
			</a>
		</div>
		
	</div>
</div>