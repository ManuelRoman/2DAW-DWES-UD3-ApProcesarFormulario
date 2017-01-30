<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="errores.jsp?pagOrigen=info.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Información</title>
<style>
	div{
	position: absolute;
	left: 35%;
	}
</style>
</head>
<body>
	<div>
		<p>Información:</p>
		<p><c:out value="${modelo}" default="Sin información"/></p>
		<form action="/procesarformulario/controlador" method="post">
			<input type="hidden" name="accion" value="index">
			<input type="submit" value="Volver al inicio">
		</form>
	</div>
</body>
</html>