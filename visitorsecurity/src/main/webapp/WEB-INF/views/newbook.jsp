<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style>
body, html {
  height: 90%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
    background-image: url('${pageContext.request.contextPath}/images/newbook-background.jpg');
      background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;  
}

.section {
	position: relative;
	height: 100vh;
}

.section .section-center {
	position: absolute;
	top: 50%;
	left: 0;
	right: 0;
	-webkit-transform: translateY(-50%);
	transform: translateY(-50%);
}

#booking {
	font-family: 'Lato', sans-serif;
	background-image: url('../background.jpg');
	background-size: cover;
	background-position: center;
	color: #191a1e;
}

.booking-form {
	position: relative;
	background: #fff;
	max-width: 642px;
	width: 100%;
	margin: auto;
	padding: 45px 25px 25px;
	border-radius: 4px;
	-webkit-box-shadow: 0px 0px 10px -5px rgba(0, 0, 0, 0.4);
	box-shadow: 0px 0px 10px -5px rgba(0, 0, 0, 0.4);
}

.booking-form .form-group {
	position: relative;
	margin-bottom: 20px;
}

.booking-form .form-control {
	background-color: #fff;
	height: 65px;
	padding: 0px 15px;
	padding-top: 24px;
	color: #191a1e;
	border: 2px solid #dfe5e9;
	font-size: 16px;
	font-weight: 700;
	-webkit-box-shadow: none;
	box-shadow: none;
	border-radius: 4px;
	-webkit-transition: 0.2s all;
	transition: 0.2s all;
}

.booking-form .form-control::-webkit-input-placeholder {
	color: #dfe5e9;
}

.booking-form .form-control:-ms-input-placeholder {
	color: #dfe5e9;
}

.booking-form .form-control::placeholder {
	color: #dfe5e9;
}

.booking-form .form-control:focus {
	background: #f9fafb;
}

.booking-form input[type="date"].form-control:invalid {
	color: #dfe5e9;
}

.booking-form select.form-control {
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

.booking-form select.form-control+.select-arrow {
	position: absolute;
	right: 6px;
	bottom: 6px;
	width: 32px;
	line-height: 32px;
	height: 32px;
	text-align: center;
	pointer-events: none;
	color: #dfe5e9;
	font-size: 14px;
}

.booking-form select.form-control+.select-arrow:after {
	content: '\279C';
	display: block;
	-webkit-transform: rotate(90deg);
	transform: rotate(90deg);
}

.booking-form .form-label {
	position: absolute;
	top: 6px;
	left: 20px;
	font-weight: 700;
	text-transform: uppercase;
	line-height: 24px;
	height: 24px;
	font-size: 12px;
	color: #98c9ee;
}

.booking-form .form-checkbox input {
	position: absolute !important;
	margin-left: -9999px !important;
	visibility: hidden !important;
}

.booking-form .form-checkbox label {
	position: relative;
	padding-top: 4px;
	padding-left: 30px;
	font-weight: 700;
	color: #191a1e;
}

.booking-form .form-checkbox label+label {
	margin-left: 15px;
}

.booking-form .form-checkbox input+span {
	position: absolute;
	left: 2px;
	top: 4px;
	width: 20px;
	height: 20px;
	background: #fff;
	border: 2px solid #dfe5e9;
	border-radius: 50%;
}

.booking-form .form-checkbox input+span:after {
	content: '';
	position: absolute;
	top: 50%;
	left: 50%;
	width: 0px;
	height: 0px;
	border-radius: 50%;
	background-color: #4fa3e3;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
	-webkit-transition: 0.2s all;
	transition: 0.2s all;
}

.booking-form .form-checkbox input:not(:checked)+span:after {
	opacity: 0;
}

.booking-form .form-checkbox input:checked+span:after {
	opacity: 1;
	width: 10px;
	height: 10px;
}

.booking-form .submit-btn {
	color: #fff;
	background-color: #4fa3e3;
	font-weight: 400;
	height: 65px;
	font-size: 18px;
	border: none;
	width: 100%;
	border-radius: 4px;
	text-transform: uppercase
}

.booking-cta {
	margin-top: 45px;
}

.booking-cta h1 {
	font-size: 52px;
	text-transform: uppercase;
	color: #4fa3e3;
	font-weight: 400;
}

.booking-cta p {
	font-size: 22px;
	color: #191a1e;
}
#butn{
    margin: 0 auto;
    display: block;
          padding-left: 100px;
    
}

#button1, #button2{
width: 150px;
height: 60px;
  background-color: white;
  color: black;
  border: 2px solid #e7e7e7;
  background-color: #008CBA;
  border-radius: 12px;
font-size: 20px;
  color: white;

}
</style>
</head>
<body>
	<div id="booking" class="section">
		<div class="section-center">
			<div class="container">
				<div class="row">
					
					<div class="booking-form">
						<form:form action="/user/newbooking" method="post" modelAttribute="booking">
							<div>
							<h2 style="text-align: center"> Make Your Reservation</h2></div>
							<p style="color:red">${bookerror}</p>
							<div class="form-group">
								<input class="form-control" id ="uname" placeholder="${loggedUser.username}" readonly/>
								<span class="form-label">Name</span>
							</div>
							
							<div class="form-group">
								<form:input path="nop" class="form-control" id="nop" placeholder="No of Persons" required="required"/>
								<span class="form-label">No of Persons</span>
							</div>
							
							
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<form:input path="date" class="form-control" id="date" type="date" required="required"/>
										<span class="form-label">Date</span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<form:input path="time" class="form-control" id= "time" type="time" required="required"/>
										<span class="form-label"> Time</span>
									</div>
								</div>
							</div>
							<div id="butn">
								<form:button type="button home-button" id="button1" >Book</form:button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="window.location.href='/user/home'" id="button2">Back</button>
							</div>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</body>
</html>