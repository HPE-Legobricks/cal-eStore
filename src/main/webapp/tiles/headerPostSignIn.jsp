<%@page import="java.util.HashMap"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.Map" %>

<script>
	var catgForComp = <%=session.getAttribute("catgForComp")%>;
	var prodCompList = <%=session.getAttribute("prodCompList")%>;
	var cartItemsMap = <%=new ObjectMapper().writeValueAsString(session.getAttribute("cartItemsMap"))%>;
	//alert(cartItemsMap);
	
	function addToCompare(productId, categoryId) {
		if (prodCompList.length == 3) {
			$('body').scrollTop(0);
			displayMsg('Maximum three products allowed for comparison');
			return;
		}
		if (prodCompList.indexOf(productId) != -1) {
			$('body').scrollTop(0);
			displayMsg('Product already marked for comparison.');
			return;
		}
		if (catgForComp == null) {
			catgForComp = categoryId;
		} else {
			if (categoryId != catgForComp) {
				$('body').scrollTop(0);
				displayMsg('Only products of same category allowed for comparison.');
				return;
			}
		}
		$
				.ajax({
					type : "GET",
					url : "markForComparision?productId=" + productId
							+ "&categoryId=" + categoryId,
					cache : false,
					success : function(data) {
						prodCompList.push(productId);
						$('body').scrollTop(0);
						displayMsg('Product marked for comparison');
						console.log("success");
						document.getElementById('prodCompListDiv').innerHTML = prodCompList.length;
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert(xhr.status);
						alert(thrownError);
						console.log("error");
						console.log(xhr.status);
						console.log(thrownError);
					}
				});
	}

	function compareProducts() {
		if (prodCompList.length < 1) {
			 $('body').scrollTop(0);
			displayMsg('A minimum of two products required for comparison.');
			return; 
		}
		window.location.href = "compareProducts";
	}

	function addTocart(productId) {
		if (!(productId in cartItemsMap)) {
			cartItemsMap[productId] = 1;
			/* alert('add to cart'); */
		} else {
			displayMsg('Product already exist in your Cart');
			var qty = cartItemsMap[productId];
			qty++;
			cartItemsMap[productId] = qty;
		}

		$.ajax({
			type : "GET",
			url : "addForCart?productId=" + productId,
			cache : false,
			success : function(data) {
				 $('body').scrollTop(0);
				displayMsg('Product added to cart');
				console.log("success");
				document.getElementById('cartItemsDiv').innerHTML = Object
						.keys(cartItemsMap).length;
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
				console.log("error");
				console.log(xhr.status);
				console.log(thrownError);
			}
		});

	}

	function proceedToCart() {
		if (Object.keys(cartItemsMap).length < 1) {
			displayMsg('A minimum of one product is required to view your Cart.');
			return; 
		}
		window.location.href = "cartDetail";
	}

	function displayMsg(msg) {
	    var x = document.getElementById("snackbar");
	    x.innerHTML = msg;
	    x.className = "show";
	    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	}

	function removeFromCart(productId) {
		/* alert((Object
				.keys(cartItemsMap).length)); */
		for ( var obj in cartItemsMap) {
			if (cartItemsMap.hasOwnProperty(productId)) {
				delete cartItemsMap[productId];
			}
		}
		/* }
			} 
			
			Object.keys(cartItemsMap)
			 if ((productId in cartItemsMap)) {
				delete cartItemsMap.productId;
				alert((cartItemsMap).length); 
			} else { 
				 alert('eles part'); 
			} */

		$.ajax({
			type : "GET",
			url : "removeFromCart?productId=" + productId,
			cache : false,
			success : function(data) {
				$('body').scrollTop(0);
				displayMsg(' Product removed from the cart ');
				console.log("success");
				/* alert(Object.keys(cartItemsMap).length); */
				document.getElementById('cartItemsDiv').innerHTML = (Object
						.keys(cartItemsMap).length)
				/* window.location.href = "cartDetail"; */
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
				console.log("error");
				console.log(xhr.status);
				console.log(thrownError);
			}
		});

	}
</script>

<div id="snackbar"></div>

<div class="headerstrip">
	<div class="container">
		<div class="pull-left welcometxt">
			An official website of the United States government.
		</div>
		<!-- Top Nav Start -->
		<div class="pull-right">
			<div class="navbar" id="topnav">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a class="home active" href="productCatalogue"><i
								class="icon-home"></i> Home </a></li>
						<li><a class="Track Order" href="oderTrack"><i
								class="icon-ok-circle"></i> Track Order </a></li>
						<!-- <li><a class="shoppingcart" href="cartDetail"><i
								class="icon-shopping-cart"></i> Cart </a></li> -->
						<li><a class="checkout" ><i
								class=" icon-user"></i><%= request.getUserPrincipal().getName() %></a></li> 
						<li><a class="checkout" href="loginform/signout"><i
								class="icon-minus-sign"></i> Logout </a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- Top Nav End -->
		<!--  <div class="pull-right">
			<ul class="nav language pull-left">
				<li class="dropdown hover"><a href="#" class="dropdown-toggle"
					data-toggle="">English <b class="caret"></b></a>
					<ul class="dropdown-menu language">
					</ul></li>
			</ul>
		</div> -->
	</div>
</div>
<div class="container">
	<div class="headerdetails">
		<a class="logo pull-left"  style="cursor: pointer;" onclick="location.href='productCatalogue'"><img title="SimpleOne"
			alt="SimpleOne" src="img/logo.png"></a>
		<div class="pull-left">
			<!--  <form class="form-search top-search">
				<input type="text" class="input-medium search-query"
					placeholder="Search Here">
				<button class="btn btn-orange btn-small tooltip-test"
					data-original-title="Search">
					<i class="icon-search icon-white"></i>
				</button>
			</form> -->
		</div>

		<ul class="nav topcart pull-right topcartm">
			<li class="dropdown hover carticon "><a style="cursor: pointer;"
				onclick="javascript:proceedToCart()" class="dropdown-toggle"> <i
					class="icon-shopping-cart font18"></i>Cart<span id="cartItemsDiv"
					class="label label-orange font14 cartlabelitems"><%=((java.util.HashMap<Integer, Integer>) session
					.getAttribute("cartItemsMap")).size()%></span>

			</a></li>
		</ul>
		<ul class="nav topcart pull-right topcartm">
			<li class="dropdown hover carticon "><a
				href="javascript:compareProducts()" class="dropdown-toggle"> <i
					class="icon-refresh font18"></i>Compare<span id="prodCompListDiv"
					class="label label-orange font14 cartlabelitems"><%=((java.util.List) session.getAttribute("prodCompList"))
					.size()%></span>
			</a></li>
		</ul>
	</div>
</div>
