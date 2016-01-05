<%--
  Created by IntelliJ IDEA.
  User: miroslav
  Date: 14.11.15
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
      <meta charset="utf-8"/>
      <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
      <meta content="width=device-width, initial-scale=1" name="viewport"/>
      <meta content="" name="description"/>
      <meta content="" name="author"/>
      <link rel="stylesheet" href="css/bootstrap.min.css"/>
      <link rel="stylesheet" href="signin.css"/>
    <title>Sign in</title>
  </head>
  <body>
  <div class="container">
      <form class="form-signin" action="/check" method="post">
          <h2 class="form-signin-heading">
              Please sign in
          </h2><br/>
              <c:if test="${check==0}">
                  <h3 style="font-size: 16px; color: red; text-align: center ">Такого пользователя не существует</h3>
              </c:if>

          <label class="sr-only" for="inputLogin">
              Login
          </label>
          <input autocomplete="off" id="inputLogin" class="form-control" type="text" name="login" autofocus="" required="" placeholder="Login"/>
          <label class="sr-only" for="inputPassword">
              Password
          </label>
          <input autocomplete="off" id="inputPassword" class="form-control" type="password" name="password" required="" placeholder="Password"/>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
              Sign in
          </button>
          <a href="/reg">
              <button class="btn btn-lg btn-primary btn-block" type="button">
                  Register
              </button>
          </a>
      </form>
  </div>
  </body>
</html>
