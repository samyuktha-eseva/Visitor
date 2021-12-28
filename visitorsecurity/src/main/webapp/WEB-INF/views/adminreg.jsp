<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style>
	.login-form {
		width: 340px;
		margin: 150px auto;
	}
    .login-form form {        
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
	.input-group-addon .fa {
        font-size: 18px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
#button1, #button2{
width: 100px;
height: 40px;
  background-color: white;
  color: black;
  border: 2px solid #e7e7e7;

}

#butn{
    margin: 0 auto;
    display: block;
}

body, html {
  height: 90%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  background-image: url('${pageContext.request.contextPath}/images/reg-log-background.jpg');
      background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
    
  
}
    
</style>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<div class="login-form">
 	<form:form class="form-horizontal" role="form" action="/adminregister" method="post" modelAttribute="user">
 		
 		<h2 class="text-center">Admin Registration</h2>
 		${error}<br/>
 		<form:errors path="username"/>
 		<div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><em class="fa fa-user"></em></span>
                <form:input path="username" class="form-control" placeholder="Username"/>
            </div>
        </div>
        
        <form:errors path="password"/>
 		<div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><em class="fa fa-lock"></em></span>
                <form:password path="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        
        <form:errors path="email"/>
 		<div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><em class="fa fa-envelope"></em></span>
                <form:input path="email" class="form-control" placeholder="Email"/>
            </div>
        </div>
        
        <form:errors path="phno"/>
 		<div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><em class="fa fa-mobile-phone"style="font-size:25px"></em></span>
                <form:input path="phno" class="form-control" placeholder="Phone Number"/>
            </div>
        </div>
        <div class="form-group">
            <form:button class="btn btn-primary btn-block">Register</form:button>
 		 </div>
		
		<div id="butn">
			<button type="button" onclick="window.location.href='/'" id="button1">Home</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="window.location.href='/login'" id="button2">Login</button>
		</div>
 		</form:form>
		
		
</div>		
</body>
</html>