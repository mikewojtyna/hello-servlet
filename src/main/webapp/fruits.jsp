<%--<%@ page import="java.util.Collection" %>--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: goobar
  Date: 07.10.18
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fruits</title>
</head>
<body>
Fruits:
<br/>
<%--<ol>
    <% for(String fruit: (Collection<String>)request.getAttribute("fruits") )
    { %>
    <li><% out.print(fruit); %></li>
    <%}%>
</ol>--%>

<ol>
    <c:forEach var="fruit" items="${fruits}" varStatus="status">
        <c:set var="isEven" value="${status.index %2 == 0}"/>
        <c:if test="${isEven}">
            <li style="background-color: red"><c:out value="${fruit}"/></li>
        </c:if>
        <c:if test="${!isEven}">
            <li style="background-color: blue"><c:out value="${fruit}"/></li>
        </c:if>
    </c:forEach>
</ol>

</body>
</html>
