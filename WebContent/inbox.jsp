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
		<div class="navbar navbar-default navbar-static" style="opacity: 0.8;">
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
				<button class="btnc btn-primary btn-md">Compose</button>
			</div>
			<div class="col-md-10" style="display: inline-block;">
				<h1 class="">${tittle}</h1>

			</div>
		</div>
		<div class="col-md-2">
			<div class="wrap">
				<ul class="nav">
					<c:if test="${user.rol == 'Cliente'}">
						<li><a href="sentmail" class="">Sent mail</a></li>
						<li><a href="drafts" class="">Drafts</a></li>
						<li><a href="deleted" class="">Deleted</a></li>
					</c:if>
					<c:if test="${user.rol == 'Administrador'}">
						<li><a href="users" class="">Users</a></li>
					</c:if>
					<li><a href="#" class="">Profile</a></li>
					<li><a href="#" class="">Contacts</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
			<!-- Tab panes -->
			<div class="wrap">
				<c:if test="${user.rol == 'Cliente'}">
					<c:forEach var="entry" items="${mailList}">
						<div id="accordion" class="panel-group"
							style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px;">
							<div class="panel panel-default" style="opacity: 0.6;">
								<div class="panel-heading" style="margin: 0px;">
									<label class=""> <input type="checkbox" class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</label> <a data-toggle="collapse" data-parent="#accordion"
										href="#${entry.id}" class="collapsed" class="list-group-item">
										<span class="name" style="min-width: 120px;">${entry.asunto}</span>
										<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<span>${entry.fechahora}</span> <span class="pull-right"></span>
									</a>
								</div>
								<div id="${entry.id}" class="panel-collapse collapse"
									style="height: 0px;">
									<div class="panel-body">
										<p class="">${entry.cuerpo}</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${user.rol == 'Administrador'}">
					<form class="form-login" action="activate" method="post">
						<c:if test="${not empty inactiveUserList}">
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
								<h4 style="color: #CECEF6; display: inline-block;">Inactive
									Users</h4>
							</div>
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
								<button class="btn btn-primary btn-md"
									style="display: inline-block; padding: 0px; padding-right: 2x; padding-left: 2x;">Activate</button>
							</div>
						</c:if>
						<c:forEach var="entry" items="${inactiveUserList}">
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px;">
								<div class="panel panel-default" style="opacity: 0.6;">
									<div class="panel-heading" style="margin: 0px;">
										<label class=""> <input type="checkbox"
											name="toActivate" value="${entry.login}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> <a href="profile?user=${entry.login}"> <span class="name"
											style="min-width: 120px;">${entry.nombre}</span> <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<span>${entry.apellidos}</span><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<span>${entry.email}</span> <span class="pull-right"></span>
										</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
					<form class="form-login" action="deactivate" method="post">
						<c:if
							test="${not empty activeUserList && not empty inactiveUserList}">
							<br />
						</c:if>
						<c:if test="${not empty activeUserList}">
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
								<h4 style="color: #CECEF6; display: inline-block;">Active
									Users</h4>
							</div>
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
								<button class="btn btn-primary btn-md"
									style="display: inline-block; padding: 0px; padding-right: 2x; padding-left: 2x;">Deactivate</button>
							</div>
						</c:if>
						<c:forEach var="entry" items="${activeUserList}">
							<div id="accordion" class="panel-group"
								style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px;">
								<div class="panel panel-default" style="opacity: 0.6;">
									<div class="panel-heading" style="margin: 0px;">
										<label class=""> <input type="checkbox"
											name="toDeactivate" value="${entry.login}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> <a href="profile?user=${entry.login}"><span class="name"
											style="min-width: 120px;">${entry.nombre}</span> <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<span>${entry.apellidos}</span><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<span>${entry.email}</span> <span class="pull-right"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
				</c:if>
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