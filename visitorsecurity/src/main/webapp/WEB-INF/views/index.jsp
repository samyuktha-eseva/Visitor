<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Index Page</title>
<style>

.nav a {
  float: right;
  color: black;
  text-align: left;
  padding: 20px 22px;
  text-decoration: none;
  font-size: 19px;
}
.nav{
position:absolute;
top:4px;
right:3px
}

#home{
background-color:yellow

}
#contact{
background-color:DodgerBlue
}
#login{
background-color:DodgerBlue
}
#register{
background-color:DodgerBlue
}
#about{
background-color:DodgerBlue
}
#home:hover{
background-color:white
}
#contact:hover{
background-color:white
}
#login:hover{
background-color:white
}
#register:hover {
background-color:white 
}
#about:hover{
background-color:white
}
.extra {
position:absolute;
top:190px;
left:570px;
color:WHITE
}
#first{
border: 3px solid #F6BE00 ;
background-color:#F6BE00;
padding:2px;
}
.eextra{
position:absolute;
top:255px;
left:520px;
margin:3px;
color:rgb(245,245,255);
border-style: solid ;
border-color: #808080;
background-color:rgba(0,0,0,0.5) ;
padding: 1px 4px
}

body{
	background-image:url('${pageContext.request.contextPath}/images/museum2.jpg');
}

</style>
</head>

<body>


<h1 style="text-align: center; color:red">${sessionout}</h1>

<div class="nav">
   <a id="login" href="/login"> Login</a>
  <a id="register" href="/register"> Register</a>
 <a id="home" href="/">Home</a>
</div>

<div class="extra">
  <h1 id="first">500+ Arts In One  Place</h1>
</div>
<div class="eextra">
<h1>Museum Is The Place Where</h1>
<h1>You Can See Wonders & Feel!</h1>
</div>

</body>
</html>