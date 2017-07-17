<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>

<head>

<title>Log in</title>
     <s:url value="/css/style.css" var="mainCss" />
     <link href="${mainCss}" rel="stylesheet" />
</head>

<body>

   <div id="welcome">
       <span>Login for User<span>

       </div>
   <div id = "error">
        ${requestScope.error}
   </div>


   <form id = "user_registration" action="${s:mvcUrl('userLogin').build()}" method="post">
       Login:
       <input type="text" name="login" value=""/>
       <br>
       Password:
       <input type="password" name="password" value=""/>
       <br>
       <input type="submit" value="Log in"/>
</body>
</html>