<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD><TITLE>Listado de usuarios</TITLE></HEAD>
<BODY>
	<c:forEach var="entry" items="${listaUsuarios}">
   		<a href="mostrarUsuario?login=${entry.login}">${entry.nombre} ${entry.apellidos}</a><br>
 	</c:forEach>
</BODY></HTML>