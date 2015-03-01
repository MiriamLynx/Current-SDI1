<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrap" id="inactiveUsers">
	<form class="form-login" action="activate" method="post">
		<c:if test="${not empty inactiveUserList}">
			<div id="accordion" class="panel-group"
				style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
				<h4 style="color: #CECEF6; display: inline-block;">Inactive
					Users</h4>
				<input type="text" style="display: inline-block;"
					class="form-control search" name="search" id="search"
					placeholder="search">
			</div>
			<div id="accordion" class="panel-group"
				style="margin-bottom: 0px; margin-left: 40px; margin-right: 40px; display: inline-block;">
				<button class="btn btn-primary btn-md"
					style="display: inline-block; padding: 0px; padding-right: 2x; padding-left: 2x;">Activate</button>
			</div>
			<br />
			<br />
		</c:if>
		<ul class="list">
			<c:forEach var="entry" items="${inactiveUserList}">
				<li>
					<div id="accordion" class="panel-group"
						style="margin-bottom: 0px; margin-left: 0px; margin-right: 40px;">
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
				</li>
			</c:forEach>
		</ul>
	</form>
</div>

<script src="http://listjs.com/no-cdn/list.js"></script>
<script type="text/javascript">
	var options = {
		valueNames : [ 'name', 'surname', 'email' ]
	};

	var userList = new List('inactiveUsers', options);
</script>