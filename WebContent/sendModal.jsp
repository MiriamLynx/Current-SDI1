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
				<label>Recipients:</label> <select name="recipients" id="multiple"
					class="select2" multiple="multiple" style="width: 400px;">
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
				<button type="button" class="btn btn-primary">Send message</button>
			</div>
		</div>
	</div>
</div>