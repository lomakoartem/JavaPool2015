<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ lang }" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
<title><fmt:message key="option.login" /></title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body id="page1">
<div class="main">
<header>
<div class="wrapper">
<h1>
<a href="index.jsp" id="logo"></a>
</h1>
<span id="slogan"><fmt:message key="header.haliy" /></span>
</div>
</header>
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs">
						<div class="box1">
							<div class="notes">
								<fmt:message key="lable.reg" />
							</div>
							<div class="tab-content" id="Flight">
								<form id="form_4" name="loginForm" method="POST"
									action="controller">
									<input type="hidden" name="command" value="login" />
									<div>
										<div class="row" style="margin-top: 20px;">
											<span class="left"><fmt:message key="option.nick" /></span>
											<input type="text" name="login" value="" class="input" />
										</div>

										<div class="row">
											<span class="left"><fmt:message key="option.pass" /></span>
											<input type="password" name="password" value="" class="input" />
										</div>
										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value="<fmt:message key="option.login" />" class="button1" />
											</span>
										</div>
										<div class="wrapper"
											style="text-align: center; margin-top: 10px;">
											<c:if test="${errorLoginPassMessage != null }">
												<fmt:message key="${ errorLoginPassMessage }" />
											</c:if>
											<c:if test="${ wrongAction != null }">
												<fmt:message key="${ wrongAction }" />
											</c:if>											
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</article>
			</div>
		</section>
		
		<footer>
			<div class="wrapper">
				<div class="links">
					<fmt:message key="contact.email" />
				</div>
			</div>
		</footer>
	</div>
</body>
</html>