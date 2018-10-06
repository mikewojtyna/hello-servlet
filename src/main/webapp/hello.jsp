<%--
  Created by IntelliJ IDEA.
  User: goobar
  Date: 25.08.18
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Servlet</title>
</head>
<body>
Using JSTL:
<br/>
<c:out value="hello ${name}!"/>
<br/>
Using plain JSP:
<br/>
hello <%= request.getAttribute("name") %>!
</body>
</html>