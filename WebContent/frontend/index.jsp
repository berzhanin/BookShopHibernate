<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thalia Books - Online Shop</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<br />
		<br />

		<div align="center" style="width: 80%; margin: 0 auto">
			<h2>New books:</h2>
			<c:forEach items="${listNewBook}" var="book">
				<div style="display: inline-block; margin: 20px">
					<div>
						<a href="view_book?id=${book.bookId}"> <img
							src="data:image/jpg;base64,${book.base64Image}" width="128"
							height="164" />
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
						</a>
					</div>
					<div>Rating *****</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>

				</div>
			</c:forEach>
		</div>
		<div align="center" style="clear:both">
			<h2>Best-selling:</h2>
		</div>
		<div align="center" style="clear:both">
			<h2>Most-favored Books:</h2>
		</div>
		<br />
		<br />
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>