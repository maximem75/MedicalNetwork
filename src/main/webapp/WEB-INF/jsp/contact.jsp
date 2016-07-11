<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.contact">

    <tiles:putAttribute name="body">

        <div id="middle">
			<div class="content_middle">
				<div class="contact_manager">
					<div class="contact_header">
						<div class="divider"></div>
						<div id="tab_contact_id" class="tab_contact active">
							<label class="tab_text active">Contacts</label>
						</div>
						<div class="divider"></div>
						<div id="tab_pending_id" class="tab_contact">
							<label class="tab_text">Invitations en cours</label>
						</div>
					</div>
					<div class="tab_container">
						<div class="content_style">
							<div id="contact_content" class="tab_content">
								<div class='div_table'>

								</div>
							</div>
							<div id="pending_content" class="tab_content">
								<div class='div_table'>

								</div>
							</div>
						</div>
					</div>
      			</div>
            </div>
        </div>

    </tiles:putAttribute>

</tiles:insertDefinition>
