<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="headerstrip">
	<div class="container">
		<div class="pull-left welcometxt">
			An official website of the United States government, <a
				class="orange" href="#">Here's how you know</a>
		</div>
		<!-- Top Nav Start -->
		<div class="pull-right">
			<div class="navbar" id="topnav">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a class="home active" href="productCatalogue"><i class="icon-home"></i>
								Home </a></li>
						<li><a class="Track Order" href="oderTrack"><i
								class="icon-user"></i> Track Order </a></li>
						<li><a class="shoppingcart" href="cartDetail"><i
								class="icon-shopping-cart"></i> Cart </a></li>
						<li><a class="checkout" href="oderConfirmation"><i
								class="icon-ok-circle"></i> CheckOut </a></li>
						<li><a class="checkout" href="loginform/signout"><i
								class="icon-minus-sign"></i> Logout </a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- Top Nav End -->
		<div class="pull-right">
			<ul class="nav language pull-left">
				<li class="dropdown hover"><a href="#" class="dropdown-toggle"
					data-toggle="">English <b class="caret"></b></a>
					<ul class="dropdown-menu language">
						<li><a href="#">English</a></li>
						<li><a href="#">Spanish</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>
<div class="container">
	<div class="headerdetails">
		<a class="logo pull-left" href="productCatalogue"><img title="SimpleOne"
			alt="SimpleOne" src="img/logo.png"></a>
		<div class="pull-left">
			<form class="form-search top-search">
				<input type="text" class="input-medium search-query"
					placeholder="Search Here...">
				<button class="btn btn-orange btn-small tooltip-test"
					data-original-title="Search">
					<i class="icon-search icon-white"></i>
				</button>
			</form>
		</div>
		<div class="pull-left topcartm">
			<button class="comparebtn pull-left" onclick="/compareProducts">Compare</button>
			<span class="label label-orange font14 cartlabel">3</span>
		</div>
		<ul class="nav topcart pull-right topcartm">
			<li class="dropdown hover carticon "><a href="#"
				class="dropdown-toggle"> <i class="icon-shopping-cart font18"></i>Cart<span
					class="label label-orange font14 cartlabelitems">1 item(s)</span>
				<!--<b class="caret"></b>--></a></li>
		</ul>
	</div>
</div>
