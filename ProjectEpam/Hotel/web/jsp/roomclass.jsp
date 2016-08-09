<%--
  Created by IntelliJ IDEA.
  User: lomak
  Date: 24.01.2016
  Time: 12:10
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
    <title><fmt:message key="roomClass.label.title" bundle="${rb}" /></title>
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body>
<c:import url="adminHeader.jsp"/>
<jsp:useBean id="roomClasses" class="ua.epam.web.bean.RoomClassQuery" scope="page" />

<form method="post" action="./EngineServlet">
    <input type="hidden" name="command" value="createRoomClass">
    <input type="text" name = "name">
    <input type="submit" value=<fmt:message key="roomClass.label.add" bundle="${rb}" />>
</form>

<table border="1">
    <caption><fmt:message key="roomClass.label.caption" bundle="${rb}" /></caption>
    <tr>
        <th><fmt:message key="roomClass.label.class" bundle="${rb}" /></th>
        <th><fmt:message key="roomClass.label.delete" bundle="${rb}" /></th>
    </tr>
    <c:forEach var = "elem" items="${roomClasses.roomClassList}" >
        <tr>
            <td>
                    ${ elem.name }
            </td>
            <td>
                <form method="post" action="./EngineServlet">
                    <input type="hidden" name="command" value="deleteRoomClass">
                    <input type="hidden" name="id" value=${ elem.id }>
                    <input type="submit" value=" X ">
                </form>

            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>