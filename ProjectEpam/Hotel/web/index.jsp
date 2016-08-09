<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="wrapper">
        <h1>
            <a href="index.jsp" id="logo"></a>
        </h1>
        <span id="slogan"><fmt:setBundle basename = "ua.epam.resource.pagescontext" var="rb"/></span>
    </div>
</header>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html>
<head>
<meta charset="UTF-8">
<title> <fmt:message key="index.label.title" bundle="${rb}" /> </title>
<body>
<a href="./EngineServlet?command=SetLocale&locale=en_US"><b>English</b> </a>
<a href="./EngineServlet?command=SetLocale&locale=ru_RU"><b>Русский</b> </a>
<form method="POST" action="./EngineServlet">
<input type="hidden" name="command" value="Login"<a><b>Login</b></a>
<input type="text" name="login"<a><b>Password</b></a>
<input type="password" name="password">
<input type="submit" name="enter" value=<fmt:message key="index.label.enter" bundle="${rb}"/> >
</form>
</div>
<br/>${errorMessage}
<br/><a href="./registration.jsp" ><fmt:message key="index.label.registration" bundle="${rb}"/></a>
</head>
</body>
</html>