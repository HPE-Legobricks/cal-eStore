
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button aria-controls="navbar" aria-expanded="false"
				data-target="#navbar" data-toggle="collapse"
				class="navbar-toggle collapsed" type="button">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="navbar">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/adminDashboard"><i class="fa fa-tachometer"></i>
						Dashboard</a></li>
				<!--  <li><a href="index.html"><i class="fa fa-desktop"></i>
						Products</a></li> -->
				<li><a href="${pageContext.request.contextPath}/renderPaginationViewAllProducts"><i
						class="fa fa-cloud-upload"></i>Publish Products</a></li>
				<li><a
					href="${pageContext.request.contextPath}/renderPaginationViewAll"><i
						class="fa fa-user"></i> Manage Users</a></li>
			</ul>
		</div>
	</div>
</nav>
