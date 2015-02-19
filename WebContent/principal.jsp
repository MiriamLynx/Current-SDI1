<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<HTML><HEAD> <TITLE>Página principal del usuario</TITLE>
<BODY>
<jsp:useBean id="user" class="com.sdi.model.Usuario" scope="session"></jsp:useBean>
Id: <jsp:getProperty property="id" name="user"/><br>
Nombre: <jsp:getProperty property="nombre" name="user"/><br>
Apellidos: <jsp:getProperty property="apellidos" name="user"/><br>
<FORM ACTION="modificarDatos" METHOD="POST">
  Email:  <INPUT TYPE="TEXT" NAME="email" SIZE="15" VALUE="<jsp:getProperty property="email" name="user"/>">
  <INPUT TYPE="Submit" VALUE="Modificar">
</FORM>
Es Vd el usuario número: ${contador}
</BODY>
</HTML>
 