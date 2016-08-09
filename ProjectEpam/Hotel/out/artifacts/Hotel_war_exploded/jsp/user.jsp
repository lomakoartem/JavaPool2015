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
    <title><fmt:message key="user.label.title" bundle="${rb}" /></title>
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body>
<fmt:message key="user.label.welcome" bundle="${rb}" /> ${user.name} !

<form method="post" action="./EngineServlet">
    <input type="hidden" name="command" value="order">
    <fmt:message key="user.label.from" bundle="${rb}" /><input type="text" name="arraival" >
    <fmt:message key="user.label.to" bundle="${rb}" /><input  type="text" name="departure">

    <ctg:classChoose/>

    <fmt:message key="user.label.beds" bundle="${rb}" /><input  type="text" name="persons">
    <input type="submit" value=<fmt:message key="user.label.order" bundle="${rb}" />>

</form>
<br > ${acceptOrderMessage}
<br>
<jsp:useBean id="orders" class="ua.epam.web.bean.UserOrdersQuery" scope="page" >
    <jsp:setProperty name="orders" property = "user" value = "${ user }" />
</jsp:useBean>

<br><fmt:message key="user.label.bills" bundle="${rb}" />
<c:forEach var = "order" items="${orders.ordersByUser}"  >
    <c:set target="${order}" property="locale" value="${pageContext.response.locale}" />
    <br>  <ctg:bill order = "${ order }" />
</c:forEach>

<form method="POST" action="./EngineServlet">
    <input type="hidden" name="command" value="logout">
    <input type="submit" name="enter" value="<fmt:message key="user.label.exit" bundle="${rb}" />">
</form>
</body>
</html>
