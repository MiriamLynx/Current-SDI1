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
				<button class="btnc btn-primary btn-md" data-toggle="modal"
					data-target="#modalSend">Compose</button>
			</div>
			<div class="col-md-10" style="display: inline-block;">
				<h1 class="">${tittle}</h1>

			</div>
		</div>
		<!-- Send Mail Modal -->
		<div class="modal fade" id="modalSend" tabindex="-1"
			style="opacity: 0.9;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">New Message</h4>
					</div>
					<div class="modal-body">
						<select name="recipients" multiple="multiple">
							<c:forEach var="entry" items="${user.contactos}">
								<option>${entry.email}</option>
							</c:forEach>
						</select> <br /> <br /> <input type="text" name="subject"
							class="form-control input-sm chat-input" placeholder="subject">
						<br /> <label>Body:</label>
						<textarea class="form-control"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save as a
							draft</button>
						<button type="button" class="btn btn-primary">Send
							message</button>
					</div>
				</div>
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
					<li><a href="contacts" class="">Contacts</a></li>
					<li><a href="updateprofile" class="">Profile</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
			<!-- Tab panes -->
			<div class="wrap">
				<c:if test="${user.rol == 'Cliente'}">
					<c:if test="${tittle == 'Drafts'}">
						<c:forEach var="entry" items="${mailList}">
							<!-- Edit Draft Modal -->
							<div class="modal fade" id="${entry.id}" tabindex="-1"
								style="opacity: 0.9;">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<a href="drafts" type="button" class="close"
												data-dismiss="modal">Close</a>
											<h4 class="modal-title" id="myModalLabel">Edit Draft</h4>
										</div>
										<div class="modal-body">
											<input type="text" required name="subject"
												class="form-control input-sm chat-input"
												value="${entry.asunto}"> <label>Body:</label>
											<textarea class="form-control">${entry.cuerpo}</textarea>
										</div>
										<div class="modal-footer">
											<a href="drafts" type="button" class="btn btn-default"
												data-dismiss="modal">Close</a>
											<button type="button" class="btn btn-primary">Save
												changes</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:forEach var="entry" items="${mailList}">
						<div id="accordion" class="panel-group"
							style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px;">
							<div class="panel panel-default" style="opacity: 0.7;">
								<div class="panel-heading" style="margin: 0px;">
									<label class=""> </label> <a data-toggle="collapse"
										data-parent="#accordion" href="#${entry.id}" class="collapsed"
										class="list-group-item"> <span class="name"
										style="min-width: 120px;">${entry.asunto}</span> <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
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
								<div class="panel panel-default" style="opacity: 0.7;">
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
								<div class="panel panel-default" style="opacity: 0.7;">
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