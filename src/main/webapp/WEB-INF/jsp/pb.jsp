<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
       <head>
       <title>Phonebook</title>

       <s:url value="/css/style.css" var="mainCss" />
       <link href="${mainCss}" rel="stylesheet" />

       </head>
       <body>
       <div id="welcome">
       <span>Phonebook<span>

       </div>
       <div id = "error">
               ${requestScope.error}
          </div>

       <div id="form_main">
       <a href="${s:mvcUrl('userLogin').build()}">Login</a>
       <br>
       <a href="${s:mvcUrl('userRegistration').build()}">Registration</a>
       </div>
       </body>
       </html>