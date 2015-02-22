<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<TITLE>Lynxmail</TITLE>

<!-- Bootstrap CSS -->
<link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="css/inbox.css" rel="stylesheet">

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
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Options<b class="caret"></b>
				</a>

					<ul class="dropdown-menu">
						<li>
							<form action="signout" method="post" class="centered">
								<input type="hidden" name="opc" value="sign out" class="">
								<input type="submit" value="signout" class="">
							</form>
						</li>
					</ul></li>
			</ul>
		</div>
		<div class="container-head">
			<button class="btn btn-default">Compose</button>
		</div>
		<div class="col-md-2">
			<div class="wrap">
				<ul class="nav">
					<li><a href="#" class="">Drafts</a></li>
					<li><a href="#" class="">Sent mail</a></li>
					<li><a href="#" class="">Inbox</a></li>
					<li><a href="#" class="">Profile</a></li>
					<li><a href="#" class="">Contacts</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
			<div class="wrap"></div>
		</div>
	</div>
	<!-- BOOTSTRAP CORE JS -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
</BODY>
</HTML>