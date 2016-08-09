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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename = "ua.epam.resource.pagescontext" var="rb"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="orders.label.title" bundle="${rb}" /></title>
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body>
<c:import url="adminHeader.jsp"/>
<jsp:useBean id="ordersQuery" class="ua.epam.web.bean.UnprocessedOrdersQuery" scope="page" />


<table border="1">
    <caption><fmt:message key="orders.label.caption" bundle="${rb}" /></caption>
    <tr>
        <th><fmt:message key="orders.label.orderNumber" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.arraivalDate" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.departureDay" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.roomClass" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.beds" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.name" bundle="${rb}" /></th>
        <th><fmt:message key="orders.label.eMail" bundle="${rb}" /></th>
        <th></th>
    </tr>
    <c:set var = "unprocessedOrders" value = "${ordersQuery.unprocessedOrders}"/>
    <c:forEach var = "elem" items="${unprocessedOrders}" >
        <tr>
            <td>
                    ${ elem.id }
            </td>
            <td>
                <fmt:formatDate pattern="dd.MM.yyyy" value =  "${ elem.arraival }" />
            </td>
            <td>
                <fmt:formatDate pattern="dd.MM.yyyy"  value = "${ elem.departure }" />
            </td>
            <td>
                    ${ elem.roomClass }
            </td>
            <td>
                    ${ elem.beds }
            </td>
            <td>
                    ${ elem.user.name }
            </td>
            <td>
                    ${ elem.user.email }
            </td>
            <td>
                <form method="post" action="./EngineServlet">
                    <input type="hidden" name="command" value="pickUpRoom">
                    <input type="hidden" name="id" value=${ elem.id }>
                    <input type="submit" value="подобрать номер">
                </form>

            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${not empty  processingOrderId}">

    <c:set target="${ ordersQuery }" property="orderId" value="${processingOrderId}"/>
    <c:set  var="processingOrder" value="${ ordersQuery.order }" scope="session"/>
    <jsp:useBean id="pickUpRoomQuery" class="ua.epam.web.bean.PickUpRoomQuery" scope="page" >
        <jsp:setProperty name="pickUpRoomQuery" property = "order" value = "${processingOrder}" />
    </jsp:useBean>

    <br>

    <table border="1">
        <caption><fmt:message key="orders.label.pickUpOrder" bundle="${rb}" /> № ${ processingOrder.id }</caption>
        <tr>
            <th><fmt:message key="orders.label.roomNumber" bundle="${rb}" /></th>
            <th>Класс</th>
            <th>Количество мест</th>
            <th></th>
        </tr>
        <c:forEach var = "elem" items="${pickUpRoomQuery.roomsList}"  varStatus="status">
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
                        <input type="hidden" name="command" value="setRoom">
                        <input type="hidden" name="roomId" value=${ elem.id }>
                        <input type="submit" value=<fmt:message key="orders.label.choose" bundle="${rb}" />>
                    </form>

                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>

</body>
</html>