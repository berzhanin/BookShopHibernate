<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} - Thalia Books - Online Shop</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<div align="center" style="width: 80%; margin: 0 auto;">
				<h2>Results for "${keyword}":</h2>
				<c:forEach items="${result}" var="book">
					<div>
						<div style="display: inline-block; margin: 20px; width= 10%" align="left">
							<div>
								<a href="view_book?id=${book.bookId}"> <img
									src="data:image/jsp;base64,${book.base64Image}" width="128"
									height="164" />
								</a>
							</div>
						</div>
						<div
							style="display: inline-block; margin: 20px; vertical-align: top;  width= 60%"
							align="left">
							<div>
								<h2>
									<a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a>
								</h2>
							</div>

							<div>Rating *****</div>
							<div>
								<i>by ${book.author}</i>
							</div>
							<div>
								<p>${fn:substring(book.description,0,100)}...</p>
							</div>
						</div>
						<div
							style="display: inline-block; margin: 20px; vertical-align: top;">
							<h3>$${book.price}</h3>
							<h3><a href="">Add to Cart</a></h3>
						</div>
					</div>
				</c:forEach>
				<h2>Best-selling:</h2>
				<h2>Most-favored Books:</h2>
				<br /> <br />
			</div>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>