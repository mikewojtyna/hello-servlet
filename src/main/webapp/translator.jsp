<%--
  Created by IntelliJ IDEA.
  User: goobar
  Date: 08.09.19
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Translator</title>
</head>
<body>

<table>
    <thead>
    <td>
        PL
    </td>
    <td>
        EN
    </td>
    </thead>
    <tbody>

    <c:forEach var="word" items="${words}">
        <tr>
            <td>
                <c:out value="${word.pl}"/>
            </td>
            <td>
                <c:out value="${word.en}"/>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
