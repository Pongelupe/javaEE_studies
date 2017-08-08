<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
		Bem vindo ao nosso gerenciador de empresas!<br />
	</p>
	<form action="controladora" method="POST">
	<input type="hidden" name="tarefa" value="NovaEmpresa">
		<p>Nome:</p>
		<input type="text" name="nome">
		<input type="submit" value="enviar">
	</form>
	<form action="controladora" method="POST">
	<input type="hidden" name="tarefa" value="Login">
		<p>Email:</p>
		<input type="text" name="email">
		<p>Senha:</p>
		<input type="password" name="senha">
		<input type="submit" value="enviar">
	</form>
	<form action="controladora?tarefa=Logout" method="POST">
		<input type="submit" value="Logout">
	</form>
</body>
</html>