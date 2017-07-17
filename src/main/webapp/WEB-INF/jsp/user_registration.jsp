<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
     <s:url value="/css/style.css" var="mainCss" />
           <link href="${mainCss}" rel="stylesheet" />


</head>
<body>

    <div id="welcome">
       <span>Registration for User<span>

      </div>

   <div id = "error">
        ${requestScope.error}
   </div>

<div>

   <sf:form modelAttribute="user_registration">


    <fieldset>
        <div>
            <label for="name">
                <s:message code="reg.name"/>
            </label>
            <sf:input path="name"/>
            <sf:errors path="name" cssClass="error"/>
        </div>
        <div>
            <label for="surname">
                <s:message code="reg.surname"/>
            </label>
            <sf:input path="surname"/>
            <sf:errors path="surname" cssClass="error"/>
        </div>
        <div>
                            <label for="patronymic">
                                <s:message code="reg.patronymic"/>
                            </label>
                            <sf:input path="patronymic"/>
                            <sf:errors path="patronymic" cssClass="error"/>
                   </div>

        <div>
                   <label for="login">
                <s:message code="reg.login"/>
            </label>
            <sf:input path="login" type="login"/>
            <sf:errors path="login" cssClass="error"/>


         </div>


        <div>
            <label for="password">
                <s:message code="reg.password"/>
            </label>
            <sf:input path="password" type="password"/>
            <sf:errors path="password" cssClass="error"/>
        </div>
        <div id ="text">
            <input type="submit" value="Registration"/>
        </div>
    </fieldset>
</sf:form>

</div>
</body>
</html>