<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />

<html>

<head>
<title><fmt:message key="label.admin" /></title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>

<body id="page1">
	<div class="main">
		<!--header -->
		<header>
			<div class="wrapper">
				<h1>
					<a href="index.jsp" id="logo"></a>
				</h1>
				<span id="slogan"><fmt:message key="header.haliy" /></span>
				<table class="top_bottons">
					<tr>
						<td style="width: 300px;"><p
								style="line-height: 20px; padding: 0; margin-bottom: 20px;">
								${user}</p></td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="logout" /><input
									class="button1" type="submit"
									value=<fmt:message key="option.logout" /> />
							</form>
						</td>
						<td>
							<form action="controller" method="post">
								<input type="hidden" name="command" value="backToAdmin" /><input
									class="button1" type="submit"
									value=<fmt:message key="back.flights" /> />
							</form>
						</td>
					</tr>
				</table>
			</div>
		</header>
		<!-- / header -->
		<!--content -->
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="employee.new" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="registerEmployee" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="position.name" />
												: </span> <select class="input" name="position">
												<option disabled><fmt:message key="position.select" /></option>
												<c:forEach var="pos" items="${positions}">
													<option value="${ pos }">${ pos }</option>
												</c:forEach>
											</select>

										</div>
										<div class="row">
											<span class="left"><fmt:message key="employee.name" />
												: </span> <input required="required" type="text" name="name"
												value="" class="input" />
										</div>
										<div class="row">
											<span class="left"><fmt:message key="employee.surname" />
												: </span> <input required="required" type="text" name="surname"
												value="" class="input" />
										</div>


										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value=<fmt:message key="employee.register" />
												class="button1" />
											</span>
										</div>

										<span class="right relative"> <c:if
												test="${ employeeNotAdded != null }">
												<fmt:message key="${ employeeNotAdded }" />
											</c:if> <c:if test="${ employeeAdded != null }">
												<fmt:message key="${ employeeAdded }" />
											</c:if>
										</span>
									</div>
								</form>
							</div>
						</div>
						<div class="box1" style="margin-bottom: 20px;">
							<div class="notes">
								<fmt:message key="employee.modify" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" action="controller" method="post">
									<input type="hidden" name="command" value="modifyEmployee" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message
													key="employee.employee" /> : </span> <select class="input"
												name="employeeId">
												<option disabled><fmt:message key="position.select" /></option>
												<c:forEach var="modifiedEmployee" items="${employees}">
													<option value="${ modifiedEmployee.id }">${ modifiedEmployee.position }:
														${ modifiedEmployee.name } ${ modifiedEmployee.surname }</option>
												</c:forEach>
											</select>

										</div>


										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value=<fmt:message key="employee.modify" /> class="button1" />
											</span>
										</div>

										<span class="right relative"> <c:if
												test="${ employeeWasModified != null }">
												<fmt:message key="${ employeeWasModified }" />
											</c:if> <c:if test="${ employeeWasntModified != null }">
												<fmt:message key="${ employeeWasntModified }" />
											</c:if>

										</span>
									</div>
								</form>
							</div>
						</div>
					</div>
				</article>




			</div>
		</section>
		<!--content end-->
		<!--footer -->
		<footer>
			<div class="wrapper">
				<div class="links">
					<fmt:message key="contact.email" />
				</div>
			</div>
		</footer>
		<!--footer end-->
	</div>
</body>
</html>