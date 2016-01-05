<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miroslav
  Date: 16.11.15
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приветствую</title>
</head>
<body>
<h1 style="text-align: center; font-size: 30px; color: darkmagenta ">Приветствую тебя <br/><c:out value="${user.fistName}"/> <c:out value="${user.lastName}"/></h1>




</body>
</html>
