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
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename = "ua.epam.resource.pagescontext" var="rb"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="rooms.label.title" bundle="${rb}" /></title>
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body>
<c:import url="adminHeader.jsp"/>
<form method="post" action="./EngineServlet">
    <input type="hidden" name="command" value="createRoom">
    <fmt:message key="rooms.label.add.number" bundle="${rb}" /><input type="text" name = "number">
    <fmt:message key="rooms.label.add.places" bundle="${rb}" /><input type="text" name = "beds">
    <ctg:classChoose/>
    <input type="submit" value=<fmt:message key="rooms.label.add" bundle="${rb}" />>
</form>

<jsp:useBean id="rooms" class="ua.epam.web.bean.RoomsQuery" scope="page" />

<table border="1">
    <caption><fmt:message key="rooms.label.caption" bundle="${rb}" /></caption>
    <tr>
        <th><fmt:message key="rooms.label.number" bundle="${rb}" /></th>
        <th><fmt:message key="rooms.label.class" bundle="${rb}" /></th>
        <th><fmt:message key="rooms.label.places" bundle="${rb}" /></th>
        <th><fmt:message key="rooms.label.delete" bundle="${rb}" /></th>
    </tr>
    <c:forEach var = "elem" items="${rooms.roomsList}"  varStatus="status">
        <tr>
            <td>
                    ${ elem.number }
            </td>
            <td>
                    ${ elem.roomClass }
            </td>
            <td>
                    ${ elem.beds }
            </td>
            <td>
                <form method="post" action="./EngineServlet">
                    <input type="hidden" name="command" value="deleteRoom">
                    <input type="hidden" name="id" value=${ elem.id }>
                    <input type="submit" value=" X ">
                </form>

            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>