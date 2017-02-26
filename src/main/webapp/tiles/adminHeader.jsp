<div class="top-bar">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<p>
					An official website of the United States government <a href="#">Here's
						how you know</a>
				</p>
			</div>
			<div class="col-sm-4">
				<ul class="navbar-right list-unstyled list-inline">
					<li> 
						<i class="fa fa-user"></i> <%= request.getUserPrincipal().getName() %>
					</li>	 
					<li><a href="${pageContext.request.contextPath}/loginform/signout"><i class="fa fa-power-off"></i> Logout</a></li> 
				</ul>
			</div>
		</div>

	</div>
</div>
<div class="header-info">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<img src="<%=request.getContextPath()%>/img/logo.png" class="img-responsive">
			</div>
			<div class="col-sm-8">
				<div class="input-group">
				</div>
			</div>
		</div>
	</div>
</div>
