<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
<title><fmt:message key="lang.select" /></title>
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
						<div class="notes">
							<fmt:message key="lang.select" />
						</div>
						<div class="tab-content" id="Flight">
							<form id="form_1" name="languageForm" method="post"
								action="controller">
								<input type="hidden" name="command" value="lang" />
								<div>
									<div class="radio">
										<div class="wrapper">
											<input type="radio" name="language" value="en"
												checked="checked"> <span class="left"><fmt:message
													key="lang.en" /></span>
										</div>
										<div class="wrapper">
											<input type="radio" name="language" value="ru"> <span
												class="left"><fmt:message key="lang.ru" /></span>
										</div>
										<div class="wrapper">
											<span class="right relative"> <input type="submit"
												value="<fmt:message key="lang.go" />" class="button1" />
											</span>
										</div>
									</div>
								</div>
							</form>
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
