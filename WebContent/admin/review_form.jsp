<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/htm14/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css">
	<title>Edit Review</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Edit Review</h2>
	</div>

	<div align="center">
	
		<form action="update_review" method="post" onsubmit="return validateFormInput()">
				<input type="hidden" name="reviewId" value="${review.reviewId}"></form>
		<form>
			<table class="form">
				<tr>
					<td align="right">Book:</td>
					<td align="left"><b>${review.book.title}</b></td>
				</tr>
				<tr>
					<td align="right">Rating:</td>
					<td align="left"><b>${review.rating}</b></td>
				</tr>
				<tr>
					<td align="right">Customer:</td>
					<td align="left"><b>${review.customer.fullname}</b></td>
				</tr>
				<tr>
					<td align="right">Headline:</td>
					<td align="left">
						<input type="text" size="60" name="headline" value="${review.headline}" />
					</td>
				</tr>
				<tr>
					<td align="right">Comment:</td>
					<td align="left">
						<textarea rows="5" cols="70" name="comment">${review.comment}</textarea>	
					</td>
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
		var headline = document.getElementById("headline");
		var comment = document.getElementById("comment");

		if (headline.value.length == 0) {
			alert("headline is required!");
			headline.focus();
			return false;
		}
		return true;
		
		if (comment.value.length == 0) {
			alert("comment is required!");
			comment.focus();
			return false;
		}
		return true;
	}
</script>
</html>