<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${bookings}" var="book">
         Id = ${book.id}<br/>
         Nop = ${book.nop}<br/>
         Time = ${book.time}<br/>
         Uname = ${book.user.username}<br/>
         Uid = ${book.user.id}<br/><br/><br/>
     </c:forEach>
</body>
</html>