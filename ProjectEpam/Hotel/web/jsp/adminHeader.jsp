<%--
  Created by IntelliJ IDEA.
  User: lomak
  Date: 24.01.2016
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename = "ua.epam.resource.pagescontext" var="rb"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />

<body>
<form method="POST" action="./EngineServlet">
   <right> <input type="hidden" name="command" value="logout">
    <input type="submit" name="enter" value="Выйти">
</right>
</form>
<center>
<b><a href="./EngineServlet?command=unprocessedOrders"><fmt:message key="adminHeader.label.ordersProcessing" bundle="${rb}"/></a></b><b><a href="./EngineServlet?command=price"><fmt:message key="adminHeader.label.prices" bundle="${rb}"/></a></b>
<b><a href="./EngineServlet?command=roomClassCatalog"><fmt:message key="adminHeader.label.roomsClasses" bundle="${rb}"/></a></b>
<b><a href="./EngineServlet?command=roomsCatalog"><fmt:message key="adminHeader.label.numbers" bundle="${rb}"/></a</b>
<b><a href="./EngineServlet?command=usersList"><fmt:message key="adminHeader.label.users" bundle="${rb}"/></a></b>
</center>
<hr/>
</body>
</html>