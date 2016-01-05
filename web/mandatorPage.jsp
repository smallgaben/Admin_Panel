<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miroslav
  Date: 20.11.15
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
         <table class="table table-bordered table-striped" style="margin: 0 auto; text-align: center " >
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
                                 ${user.login}
                         </td>
                         <td>
                                 ${user.password}
                         </td>
                         <td>
                                 ${user.fistName}
                         </td>
                         <td>
                                 ${user.lastName}
                         </td>
                         <td>
                                 ${user.role.roleName}
                         </td>
                         <td>
                                 ${user.adress.country}
                         </td>
                         <td>
                                 ${user.adress.street}
                         </td>
                         <td>
                                 ${user.adress.index}
                         </td>
                         <td>
                             <c:forEach var="music" items="${user.musicTypes}">
                                 ${music.typeName}<br/>
                             </c:forEach>
                         </td>
                     </tr>
                 </c:forEach>
             </tbody>
         </table>
    </div>
</body>
</html>
