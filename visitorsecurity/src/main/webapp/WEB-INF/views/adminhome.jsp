<%@page import="com.virtusa.visitor.entities.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style>
body, html {
  height: 90%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
    background-image: url('${pageContext.request.contextPath}/images/museum2.jpg');
      background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  }

th, td {
  padding-top: 5px;
  padding-bottom: 20px;
  padding-left: 30px;
  padding-right: 40px;
  text-align: center;
}

#button1, #button2{
width: 80px;
height: 27px;
  background-color: white;
  color: black;
  background-color:#AFEEEE;
  font-weight: bold;
  border-radius: 8px;
  font-size: 12px;

}

#button3, #button4, #button5{
width: 150px;
height: 40px;
  background-color: white;
  color: black;
  background-color:#AFEEEE;
  font-weight: bold;
  border-radius: 12px;
}

#butn{
    margin: 0 auto;
    display: block;
      padding-left: 100px;
}

.login-form {
	width: 50vw;
	margin: 8vw auto;
}
	
.login-form  {        
   	margin-bottom: 15px;
    background-color:rgba(0,0,0,0.5);
    border-style: solid ;
	border-color: #AFEEEE;
    box-shadow: 20px 20px 20px rgba(0, 0, 0, 1);
    border-radius: 5px;
    padding: 15px;
}

h2{
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}

tbody{
	background-color:rgba(0, 0, 0, 0.2);
	color: white;
	font-weight: bold;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
	font-size: large;
}

table{
	width: 80%;
	align: center;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<div class="login-form">
<h2 style="text-align:center; color:#AFEEEE"><strong>Booking Info - Admin View</strong></h2>

<table class="table">
<caption hidden="hidden"> Bookings Table</caption>
  <thead>
    <tr style="background-color:#AFEEEE">
      <th scope="col">Booking Id</th>
      <th scope="col">Username</th>
      <th scope="col">Number of Persons</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Update</th>
      <th scope="col">Delete</th>
      
    </tr>
  </thead>
  <tbody>
      <c:forEach items="${bookings}" var="book">
         <tr>
         <td>${book.id}</td>
         <td>${book.user.username}</td>
         <td>${book.nop}</td>
         <td><fmt:formatDate type = "date" dateStyle="short" value = "${book.date}"/></td>
         <td><fmt:formatDate type = "time" pattern="h:mm a" value = "${book.time}"/></td>
         <td>
         <form action="/user/editbooking" method="get">
         <input type="number" name="bid" id="bid" value="${book.id}" hidden="hidden"/>
         <button type="submit" id="button1">Edit</button>
         </form>
         </td>
         <td>
         <form action="/user/deletebooking" method="post">
         <input type="number" name="bid" id="bid" value="${book.id}" hidden="hidden"/>
         <button type="submit" id="button1">Delete</button>
         </form>
         </td>
          </tr>
     </c:forEach>
      </tbody>
  
</table>
<br><br>
<div id="butn">
	<button onclick="window.location.href='/user/newbooking'" id="button3" hidden="hidden" >New Booking</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<button onclick="window.location.href='/'" id="button4">Back to Homepage</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<button onclick="window.location.href='/logout'" id="button4">Logout</button>
</div>
</div> 
</body>

</html>