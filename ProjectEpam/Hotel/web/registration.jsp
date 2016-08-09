<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />

<body>
<form action="./EngineServlet">
    <input type = "hidden" name = "command" value = "register">
    <br/><b>Login</b><center><input type = "text" name = "login"></center>
    <br/><b>Имя </b><center><input type = "text" name = "name"></center>
    <br/><b>e-mail</b><center><input type = "text" name = "email"></center>
    <br/><b>Пароль</b><center><input type = "password" name = password></center>
    <br/><b>Подтверждение пароля </b><center><input type = "password" name = "confirmPassword"></center>
    <br/>${errorMessage}
    <br/><input type = "submit" name = "register" value = "Зарегистрироваться">
</form>
<hr/>
</body>
</html>