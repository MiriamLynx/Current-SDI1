<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<HTML>
<HEAD><TITLE>Página principal del administrador</TITLE></HEAD>
<BODY>
<jsp:useBean id="user" class="com.sdi.model.Usuario" scope="session"></jsp:useBean>
Id: <jsp:getProperty property="id" name="user"/><br>
Nombre: <jsp:getProperty property="nombre" name="user"/><br>
Apellidos: <jsp:getProperty property="apellidos" name="user"/><br>
Email: <jsp:getProperty property="email" name="user"/><br>
<a href="listarUsuarios">Listar usuarios</a>
</BODY></HTML>