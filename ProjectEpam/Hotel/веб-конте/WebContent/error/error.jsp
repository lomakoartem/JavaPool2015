<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Error Page</title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body id="page1">
	<div class="main">
		<header>
			<div class="wrapper">
				<h1>
					<a href="index.jsp" id="logo"></a>
				</h1>
				<span id="slogan">A I R C O M P A N Y</span>
			</div>
		</header>
		<section id="content">
			<div class="for_banners">
				<article class="col1">
					<div class="tabs"></div>
				</article>
				<article class="col2">
					<div class="box1" style="margin-top: 245px;">
						<div class="notes">ERROR</div>
						<div>
							<table class="flight_table">
								<tr>
									<td>Request from ${pageContext.errorData.requestURI} is
										failed</td>
								</tr>
								
								<tr>
									<td>Servlet name or type: ${pageContext.errorData.servletName}</td>
								</tr>
								<tr>
									<td>Status code: ${pageContext.errorData.statusCode}</td>
								</tr>
								<tr>
									<td>Exception: ${pageContext.errorData.throwable}</td>
								</tr>
								<tr>
									<td><a href="/index.jsp" title="Return to the login page">Home
											page</a></td>
								</tr>
							</table>
						</div>
					</div>
				</article>
			</div>
		</section>
		<footer>
			<div class="wrapper">
				<div class="links"></div>
			</div>
		</footer>
	</div>
</body>
</html>