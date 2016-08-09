<%--
  Created by IntelliJ IDEA.
  User: lomak
  Date: 24.01.2016
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename = "ua.epam.resource.pagescontext" var="rb"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="users.label.title" bundle="${rb}" /></title>
</head>

<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body>
<c:import url="adminHeader.jsp"/>

<jsp:useBean id="rooms" class="ua.epam.web.bean.UsersQuery" scope="page" />
<style>
    td,th{
        border: 1px ;
        height: 30px;
        transition: all 0.3s;
    }


</style>
<center style="margin-top: 100px;">
<table border="2"  bgcolor="#dc143c">
    <caption><fmt:message key="users.label.caption" bundle="${rb}" /></caption>
    <tr>
        <th><fmt:message key="users.label.name" bundle="${rb}" /></th>
        <th><fmt:message key="users.label.login" bundle="${rb}" /></th>
        <th><fmt:message key="users.label.administrator" bundle="${rb}" /></th>
        <th><fmt:message key="users.label.delete" bundle="${rb}" /></th>
    </tr>
    <c:forEach var = "elem" items="${rooms.usersList}" >
        <tr>
            <td>
                    ${ elem }
            </td>
            <td>
                    ${ elem.login }
            </td>
            <td>
                    ${ elem.administrator }
            </td>
            <td>
                <form method="post" action="./EngineServlet">
                    <input type="hidden" name="command" value="deleteUser">
                    <input type="hidden" name="id" value=${ elem.id }>
                    <input type="submit" value=" X ">
                </form>

            </td>
        </tr>
    </c:forEach>

</table>
</center>
</body>

</html>