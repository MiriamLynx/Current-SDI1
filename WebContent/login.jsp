<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<TITLE>Lynxmail</TITLE>

<!-- Bootstrap CSS -->
<link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="css/login.css" rel="stylesheet">

<BODY>
	<div class="container">
		<div class="navbar navbar-default navbar-static">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Lynxmail</a>

			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#" class="">Home</a></li>
				<li><a href="#" class="">About</a></li>
				<li><a href="#" class="">Contact</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<form class="form-login">
					<h4 class="">Welcome back</h4>
					<input type="text" id="userName"
						class="form-control input-sm chat-input" placeholder="username">
					<br class=""> <input type="password" id="userPassword"
						class="form-control input-sm chat-input" placeholder="password">
					<br class="">
					<c:if test="${not empty error}">
						<div style="color: red; font-weight: bold;">${error}</div>
					</c:if>
					<br class="">
					<div class="wrapper">
						<input type="hidden" name="opc" value="login"><input
							type="submit" value="login" class="btn btn-primary btn-md">
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