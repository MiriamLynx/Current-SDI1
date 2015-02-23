<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<HTML>
<TITLE>Lynxmail</TITLE>

<!-- Bootstrap CSS -->
<link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="css/register.css" rel="stylesheet">

<BODY>

	<div class="container">
		<div class="navbar navbar-default navbar-static">
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
				<div class="form-login">
					<h4 class="">Enter your data</h4>

					<input type="text" id="userName"
						class="form-control input-sm chat-input" placeholder="username">
					<br class=""> <input type="text" id="name"
						class="form-control input-sm chat-input" placeholder="name">
					<br class=""> <input type="password" id="password"
						class="form-control input-sm chat-input" placeholder="password">
					<br class=""> <input type="password" id="repeatPassword"
						class="form-control input-sm chat-input"
						placeholder="repeat password"> <br class="">
					<div class="wrapper">
						<span class="group-btn"> <a href="#"
							class="btn btn-primary btn-md">submit<i class="fa fa-sign-in"></i></a>
						</span>

					</div>
				</div>
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