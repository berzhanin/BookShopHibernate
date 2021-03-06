<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create New Customer</title>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br />
	<br />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null}">
				Edit Customer
			</c:if>
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" onsubmit="return validateFormInput()">
				<input type="hidden" name="customerId" value="${customer.customerId}"></form>
		</c:if>
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" onsubmit="return validateFormInput()"></form>
		</c:if>
		<form>
			<table class="form">
				<tr>
					<td align="right">E-mail:</td>
					<td><input type="text" id="email" name="email" size="45" value="${customer.email}" /></td>
				</tr>
 				<tr>
					<td align="right">Full name:</td>
					<td><input type="text" id="fullname" name="fullname" size="45" value="${customer.fullname}" /></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td><input type="text" id="password" name="password" size="45" value="${customer.password}" />
					</td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td><input type="text" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}" />
					</td>
				</tr>
				<tr>
					<td align="right">Phone Number:</td>
					<td><input type="text" id="phone" name="phone" size="45" value="${customer.phone}" /></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td><input type="text" id="adress" name="adress" size="45" value="${customer.adress}" /></td>
				</tr>
				<tr>
					<td align="right">City</td>
					<td><input type="text" id="city" name="city" size="45" value="${customer.city}" /></td>
				</tr>
				<tr>
					<td align="right">Zip Code</td>
					<td><input type="text" id="zipCode" name="zipCode" size="45 " value="${customer.zipCode}" /></td>
				</tr>
				<tr>
					<td align="right">Country</td>
					<td><input type="text" id="country" name="country" size="45" value="${customer.country}" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button> &nbsp;&nbsp;&nbsp;
						<button onclick="javascript:history.go(-1);">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var email = document.getElementById("email");
		var fullname = document.getElementById("fullname");
		var password = document.getElementById("password");
		var confirmPassword = document.getElementById("confirmPassword");
		var phone = document.getElementById("phone");
		var adress = document.getElementById("adress");
		var city = document.getElementById("city");
		var zipCode = document.getElementById("zipCode");
		var country = document.getElementById("country");
		
		if (email.value.length == 0) {
			alert("Email is required!");
			email.focus();
			return false;
		}

		if (fullname.value.length == 0) {
			alert("Full Name is required!");
			fullname.focus();
			return false;
		}

		if (password.value.length == 0) {
			alert("Password is required!");
			password.focus();
			return false;
		}
		
		if (confirmPassword.value.length == 0) {
			alert("Confirm your Password is required!");
			confirmPassword.focus();
			return false;
		}
		
		if (phone.value.length == 0) {
			alert("Phone number is required!");
			phone.focus();
			return false;
		}
		
		if (adress.value.length == 0) {
			alert("Address is required!");
			adress.focus();
			return false;
		}
		
		if (city.value.length == 0) {
			alert("City is required!");
			city.focus();
			return false;
		}
		
		if (zipCode.value.length == 0) {
			alert("Zip Code is required!");
			zipCode.focus();
			return false;
		}
		
		if (country.value.length == 0) {
			alert("Country is required!");
			country.focus();
			return false;
		}
	}
</script>
</html>