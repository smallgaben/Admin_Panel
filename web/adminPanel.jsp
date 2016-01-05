<%--
  Created by IntelliJ IDEA.
  User: miroslav
  Date: 21.11.15
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1" name="viewport"/>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <title>Admin Control</title>
</head>
<body>
    <div class="container" style="width: 100%">
        <form id="userTable" accept-charset="UTF-8" class="form-group-lg" action="/red">
            <table class="table table-bordered table-striped" style="width: 100% margin: 0 auto; text-align: center " >
                <thead style="background-color: #204d74; color: white">
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        Login
                    </td>
                    <td>
                        Password
                    </td>
                    <td>
                        First Name
                    </td>
                    <td>
                        Last Name
                    </td>
                    <td>
                        Role
                    </td>
                    <td>
                        Country
                    </td>
                    <td>
                        Street
                    </td>
                    <td>
                        ZIP
                    </td>
                    <td>
                        Music Types
                    </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                              ${user.id}
                        </td>
                        <td>
                            <label class="sr-only" for="inputLogin">
                                Login
                            </label>
                            <input  autocomplete="off" id="inputLogin" class="form-control" name="login" type="text" value="${user.login}" autofocus="" required="">
                        </td>
                        <td><label class="sr-only" for="inputPassword">
                            Password
                        </label>
                            <input  autocomplete="off" id="inputPassword" class="form-control" name="password" type="text" value="${user.password}" autofocus="" required="">
                        </td>
                        <td>
                            <label class="sr-only" for="inputFirstName">
                                First Name
                            </label>
                            <input  autocomplete="off" id="inputFirstName" class="form-control" name="firstName" type="text" value="${user.fistName}" autofocus="" required="">
                        </td>
                        <td>
                            <label class="sr-only" for="inputLastName">
                                Last Name
                            </label>
                            <input  autocomplete="off" id="inputLastName" class="form-control" name="lastName" type="text" value="${user.lastName}" autofocus="" required="">
                        </td>
                        <td>
                            <label class="sr-only" for="inputRole">
                                Role
                            </label>
                            <select id="inputRole" name="role">
                                <option selected value="${user.role.roleName}">${user.role.roleName}</option>
                                <c:forEach var="listRole" items="${allRoles}">
                                    <option value="${listRole.roleName}">${listRole.roleName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label class="sr-only" for="inputCountry">
                                Country
                            </label>
                            <input  autocomplete="off" id="inputCountry" class="form-control" name="country" type="text" value="${user.adress.country}" autofocus="" required="">
                        </td>
                        <td>
                            <label class="sr-only" for="inputStreet">
                                Street
                            </label>
                            <input  autocomplete="off" id="inputStreet" class="form-control" name="street" type="text" value="${user.adress.street}" autofocus="" required="">
                        </td>
                        <td>
                            <label class="sr-only" for="inputIndex">
                                Index
                            </label>
                            <input  autocomplete="off" id="inputIndex" class="form-control" name="index" type="number" value="${user.adress.index}" autofocus="" required="">
                        </td>
                        <td>
                            <c:forEach var="music" items="${user.musicTypes}">
                                <label class="sr-only" for="inputMusicType">
                                    Music Type
                                </label>
                                <input  autocomplete="off" id="inputMusicType" class="form-control" name="musicType" type="text" value="${music.typeName}" autofocus="" required="">
                                <br/>
                            </c:forEach>
                        </td>
                        <td>
                            <button name="redId" value="${user.id}" type="submit">
                                <img src="contact-confirm.png" width="50px" height="50px">
                            </button>
                        </td>
                        <td>
                            <form action="/del">
                                <button name="delId" value="${user.id}" type="submit">
                                    <img src="Delete_Icon.png" width="50px" height="50px">
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <br/>
        <h2>Create new user</h2>
        <form action="/addB" class="form-group-lg">
            <table class="table table-bordered table-striped" style="width: 100% margin: 0 auto; text-align: center ">
                <tr>
                    <td><label class="sr-only" for="newLogin">
                        Login
                    </label>
                        <input  autocomplete="off" id="newLogin" class="form-control" name="login" type="text" autofocus="" required="" placeholder="Login">
                    </td>
                    <td>
                        <label class="sr-only" for="newPassword">
                            Password
                        </label>
                        <input  autocomplete="off" id="newPassword" class="form-control" name="password" type="password" autofocus="" required="" placeholder="Password">
                    </td>
                    <td>
                        <label class="sr-only" for="newFirstName">
                            First Name
                        </label>
                        <input  autocomplete="off" id="newFirstName" class="form-control" name="firstName" type="text" autofocus="" required="" placeholder="First Name">
                    </td>
                    <td>
                        <label class="sr-only" for="newLastName">
                            Last Name
                        </label>
                        <input  autocomplete="off" id="newLastName" class="form-control" name="lastName" type="text" autofocus="" required="" placeholder="Last Name">
                    </td>
                    <td>
                        <label class="sr-only" for="newCountry">
                            Country
                        </label>
                        <input  autocomplete="off" id="newCountry" class="form-control" name="country" type="text" autofocus="" required="" placeholder="Country">
                    </td>
                    <td>
                        <label class="sr-only" for="newStreet">
                            Street
                        </label>
                        <input  autocomplete="off" id="newStreet" class="form-control" name="street" type="text" autofocus="" required="" placeholder="Street">
                    </td>
                    <td>
                        <label class="sr-only" for="newIndex">
                            Index
                        </label>
                        <input  autocomplete="off" id="newIndex" class="form-control" name="index" type="text" autofocus="" required="" placeholder="Index">
                    </td>
                    <td>
                        <label class="sr-only" for="newRole">
                            Role
                        </label>
                        <select id="newRole" name="role">
                            <c:forEach var="listRole" items="${allRoles}">
                                <option value="${listRole.roleName}">${listRole.roleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <c:forEach var="musicType" items="${musicTypes}">
                            <label class="sr-only" for="newMusicType">
                                Music Type
                            </label>
                            <input id="newMusicType" type="checkbox" name="musicTypes" value="${musicType.typeId}">
                            ${musicType.typeName}<br/>
                        </c:forEach>
                    </td>
                    <td>
                        <button name="addUser" type="submit">
                            <img src="add-user.png" width="50px" height="50px">
                        </button> 
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
