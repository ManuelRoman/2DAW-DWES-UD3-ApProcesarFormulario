<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="procesaForm.modelo.beans.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
<style>
	div{
	position: absolute;
	left: 30%;
	}
</style>
</head>
<body>
	<div>
	<c:choose>
    	<c:when test="${not empty error}">
        	<p><c:out value="${error.toString()}" default="Error"/></p>
    	</c:when>
    	<c:otherwise>
       	 	<p><c:out value="${param.pagOrigen}" default="Error"/></p>
       	 	<p><%=exception.toString() %></p>
    	</c:otherwise>
	</c:choose>
	<form action="/procesarformulario/controlador" method="post">
		<input type="hidden" name="accion" value="index">
		<input type="submit" value="Volver al inicio">
	</form>
	</div>
</body>
</html>