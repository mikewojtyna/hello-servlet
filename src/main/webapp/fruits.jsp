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
    <title>Fruits</title>
</head>
<body>
Fruits:
<br/>
<c:forEach var="fruit" items="${fruits}">
    <c:out value="${fruit}"/>
</c:forEach>
</body>
</html>
