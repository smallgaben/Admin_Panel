<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miroslav
  Date: 16.11.15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1" name="viewport"/>
  <meta content="" name="description"/>
  <meta content="" name="author"/>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="registration.css"/>
</head>
<body>
  <form accept-charset="UTF-8" class="form-group-lg" action="/add" method="post">
    <h2 class="form-group-lg">Registration</h2>
      <c:if test="${wrong==0}">
          <h2 style="font-size: medium; color: red; text-align: center">
              Passwords are not equals
          </h2>
      </c:if>
      <c:if test="${wrong==1}">
          <h2 style="font-size: medium; color: red; text-align: center">
              User already exists
          </h2>
      </c:if>
    <label class="sr-only" for="inputLogin">
      Login
    </label>
    <input  autocomplete="off" id="inputLogin" class="form-control" name="login" type="text" value="${login}" autofocus="" required="" placeholder="Login">
    <label class="sr-only" for="inputPassword">
      Password
    </label>
      <input autocomplete="off" id="inputPassword" class="form-control" type="password" name="password" value="${password}" required="" placeholder="Password"/>
      <label class="sr-only" for="confrimPassword">
         Confrim Password
      </label>
      <input id="confrimPassword" class="form-control" type="password" name="confrimPassword" required=""  placeholder="Confrim Password"/>
    <label class="sr-only" for="inputFirstname">
      First Name
    </label>
    <input autocomplete="off" id="inputFirstname" class="form-control" type="text" name="firstName" value="${firstName}"  required="" placeholder="First Name"/>
      <label class="sr-only" for="inputLastname">
          Last Name
      </label>
      <input autocomplete="off" id="inputLastname" class="form-control" type="text" name="lastName"  required="" value="${lastName}" placeholder="Last Name"/>

      <label class="sr-only" for="inputCountry">
          Country
      </label>
      <input autocomplete="off" id="inputCountry" class="form-control" type="text" name="country" value="${country}"   required="" placeholder="Country"/>

      <label class="sr-only" for="inputStreet">
          Street
      </label>
      <input autocomplete="off" id="inputStreet" class="form-control" type="text" name="street" value="${street}"  required="" placeholder="Street"/>

      <label class="sr-only" for="inputZip">
          ZIP
      </label>
      <input autocomplete="off" id="inputZip" class="form-control" type="number" name="zip" content="" value="${zip}"  required="" placeholder="zip"/>
            <c:forEach var="music" items="${musicTypes}">
                   <div>
                       <input id="${music.typeId}" style="float: left" type="checkbox" name="musicTypes" value="${music.typeId}">
                       <label class="musicTypes" for="${music.typeId}">
                               ${music.typeName}<br/>
                       </label>
                   </div>
            </c:forEach>

      <button class="btn btn-lg btn-primary btn-block" type="submit">
          Registration
      </button>
      <button class="btn btn-lg btn-primary btn-block" type="reset">
          Clean
      </button>
  </form>




</body>
</html>
