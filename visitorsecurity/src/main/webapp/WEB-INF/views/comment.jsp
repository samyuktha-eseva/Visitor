<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
#registration
<form:label path="password">Password: </form:label>
 		<form:password path="password"/> <form:errors path="password"/><br/>
 		<form:label path="email">Email: </form:label>
 		<form:input path="email"/> <form:errors path="email"/><br/>
 		<form:label path="phno">Phone Number: </form:label>
 		<form:input path="phno"/> <form:errors path="phno"/><br/>
 		
 		
#login 		
 <form:label path="username">Username: </form:label>
 		<form:input path="username"/> <form:errors path="username"/><br/>
 		
 		<form:label path="password">Password: </form:label>
 		<form:password path="password"/> <form:errors path="password"/> <br/>
 		
 		<form:button>Login</form:button>
 		
 		
#login2 
<div class="form-group">
 		<form:label class="col-sm-2 control-label" path="username">Username: </form:label>
 		<div class="col-sm-6">
 		<form:input placeholder="Username" class="form-control" path="username"/> <form:errors path="username"/><br/>
 		</div>
 		</div>
 		
 		<div class="form-group">
 		<form:label class="col-sm-2 control-label" path="password">Password: </form:label>
 		<div class="col-sm-6">
 		<form:input placeholder="Password" class="form-control" path="password"/> <form:errors path="password"/><br/>
 		</div>
 		</div>
 		
 		<div class="form-group">
 		<form:label class="col-sm-2 control-label" path="email">Email: </form:label>
 		<div class="col-sm-6">
 		<form:input placeholder="Email" class="form-control" path="email"/> <form:errors path="email"/><br/>
 		</div>
 		</div>
 		
 		<div class="form-group">
 		<form:label class="col-sm-2 control-label" path="phno">Phone Number: </form:label>
 		<div class="col-sm-6">
 		<form:input placeholder="Phone Number" class="form-control" path="phno"/> <form:errors path="phno"/><br/>
 		</div>
 		</div>
 		
 		
 		<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
		<form:button class="btn btn-primary btn-block">Register</form:button>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
		<a href="/login" class="btn btn-default btn-block">Login Page</a>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
		<a href="/" class="btn btn-default btn-block">Home Page</a>
		</div>
		</div>
</body>
</html>