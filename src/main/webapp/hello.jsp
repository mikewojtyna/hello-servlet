<%--
  Created by IntelliJ IDEA.
  User: goobar
  Date: 25.08.18
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Servlet</title>
</head>
<body>
hello <%= request.getAttribute("name") %>!
</body>
</html>
