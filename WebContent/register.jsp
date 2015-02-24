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
				<li class="active"><a href="welcome.jsp" class="">Home</a></li>
				<li><a href="#" class="">About</a></li>
				<li><a href="#" class="">Contact</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<form class="form-login" action="signup" method="post">
					<h4 class="">Enter your data</h4>
					<input type="text" required name="username"
						class="form-control input-sm chat-input" placeholder="username">
					<br class=""> <input type="text" required name="name"
						class="form-control input-sm chat-input" placeholder="name">
					<br class=""> <input type="text" required name="surname"
						class="form-control input-sm chat-input" placeholder="surname">
					<br class=""> <input type="password" required name="password"
						class="form-control input-sm chat-input" placeholder="password">
					<br class=""> <input type="password" required
						name="repeatpassword" class="form-control input-sm chat-input"
						placeholder="repeat password"> <br class="">
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
						<input type="hidden" name="opc" value="signup"><input
							type="submit" value="submit" class="btn btn-primary btn-md">
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