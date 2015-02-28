<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<TITLE>Lynxmail</TITLE>

<!-- Bootstrap CSS -->
<link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="css/register.css" rel="stylesheet">

<BODY>
	<div class="container">
		<div class="navbar navbar-default navbar-static" style="opacity: 0.9;">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Lynxmail</a>
			</div>
			<ul class="nav navbar-nav">
				<c:if test="${user.rol == 'Cliente'}">
					<li class="active"><a href="sentmail" class="">Home</a></li>
				</c:if>
				<c:if test="${user.rol == 'Administrador'}">
					<li class="active"><a href="users" class="">Home</a></li>
				</c:if>
				<li><a href="#" class="">About</a></li>
				<li><a href="#" class="">Contact</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Options<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="signout" class="">Sign out</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<form class="form-login" action="newcontact" method="post">
					<h4 class="">Enter your changes</h4>
					<input type="text" required name="name"
						class="form-control input-sm chat-input" placeholder="name">
					<br class=""> <input type="text" required name="surname"
						class="form-control input-sm chat-input" placeholder="surname">
					<br class=""> <input type="text" required name="address"
						class="form-control input-sm chat-input" placeholder="address">
					<br class=""> <input type="text" name="mail"
						class="form-control input-sm chat-input" placeholder="mail">
					<br class=""><input type="text" required
						name="city" class="form-control input-sm chat-input"
						placeholder="city">
					<c:if test="${not empty error}">
						<br />
						<div style="color: red;">${error}</div>
					</c:if>
					<c:if test="${not empty exit}">
						<br />
						<div style="color: green;">${exit}</div>
					</c:if>
					<br />
					<div class="wrapper">
						<div class="wrapper" style="display: inline-block;">
							<input type="submit" value="Submit"
								class="btn btn-primary btn-md" style="display: inline-block;">
						</div>
						<div class="wrapper" style="display: inline-block;">
							<a href="contacts" class="btn btn-primary btn-md"
								style="display: inline-block;">Cancel</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- BOOTSTRAP CORE JS -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>

</BODY>
</HTML>