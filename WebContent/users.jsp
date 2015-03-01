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
		<div class="navbar navbar-default navbar-static" style="opacity: 0.9;">
			<div class="navbar-header">
				<a class="navbar-brand droppedHover" href="#">Lynxmail</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#" class="droppedHover">Home</a></li>
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
		<div class="container-head">
			<div class="col-md-2" style="display: inline-block;">
				<br class=""> <br class="">
			</div>
			<div class="col-md-10" style="display: inline-block;">
				<h1 class="">${tittle}</h1>

			</div>
		</div>
		<div class="col-md-2">
			<div class="wrap">
				<ul class="nav">
					<li><a href="users" class="">Users</a></li>
					<li><a href="contacts" class="">Contacts</a></li>
					<li><a href="updateprofile" class="">Profile</a></li>
				</ul>
			</div>
		</div>
		<script src="http://listjs.com/no-cdn/list.js"></script>
		<div class="col-md-10">
			<!-- Includes -->
			<jsp:include page="active.jsp"></jsp:include>
			<br />
			<jsp:include page="inactive.jsp"></jsp:include>
		</div>
	</div>

	<!-- BOOTSTRAP CORE JS -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
</BODY>
</HTML>