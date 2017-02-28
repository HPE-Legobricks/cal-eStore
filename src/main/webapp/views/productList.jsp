<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<div id="maincontainer">
	<section id="product">
		<div class="container">
			<!--  breadcrumb -->
			<ul class="breadcrumb">
				<li><a href="productCatalogue">Home</a> <span class="divider">/</span></li>
				<li class="active">Product List View</li>
			</ul>
			<div class="row">
				<!-- Sidebar Start-->
				<!-- Sidebar-->
				<aside class="span3">
					<!-- Category-->
					<div class="sidewidt">
						<h1 class="heading1">
							<span class="maintext"><em class="icon-th-list"></em>
								Categories</span>
						</h1>
						<ul class="nav nav-list categories">
							<c:forEach items="${category}" var="category">
								<li><a
									href="javascript:filterCategory(${category.categoryId},'${category.categoryName}')"><c:out
											value="${category.categoryName}" /></a></li>
							</c:forEach>
						</ul>
					</div>
					<!-- Latest Product -->

					<!--  Price -->
					<div class="sidewidt">
						<h1 class="heading1">
							<span class="maintext">Discounted Price</span>
						</h1>
						<section>
							<div style="position: relative; padding: 0px;">
								<div>
									<input type="text" id="range" value="" name="range" />
								</div>
							</div>
						</section>
					</div>

					<!--  Brand -->

					<div class="sidewidt">
						<h1 class="heading1">
							<span class="maintext">Brand</span>
						</h1>
						<section>
							<div class="controls">
								<c:forEach items="${brand}" var="brand">
									<label class="checkbox"> <input type="checkbox"
										value="option" name="orange"
										onclick="javascript:filterBrand(${brand.brandId}, this)">
										<c:out value="${brand.brandName}" />
									</label>
								</c:forEach>
							</div>
						</section>
					</div>
					<!--  Brand Ends -->
				</aside>

				<!-- Sidebar End-->
				<!-- Category-->
				<div class="span9">
					<!-- Category Products-->
					<section id="category">
						<div class="row">
							<div class="span9">
								<!-- Sorting-->
								<div class="sorting well">
									<form class=" form-inline pull-left" id="divIcons">
										Sort By : <select class="span2" id="dynamic_select">
											<option
												value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=&Order=&option=default">Default</option>
											<option label="Price Low to High"
												value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=pricePerUnit&Order=asc&option=Price Low to High">Price
												Low to High</option>
											<option label="Price High to Low"
												value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=pricePerUnit&Order=desc&option=Price High to Low">Price
												High to Low</option>
											<option label="Rating"
												value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=rating&Order=desc&option=Rating">Rating</option>
										</select>

									</form>
									<!-- <div class="btn-group pull-right">
										<button class="btn btn-orange" id="list">
											<i class="icon-th-list"></i>
										</button>
										<button class="btn " id="grid">
											<i class="icon-th icon-white"></i>
										</button>
									</div> -->
								</div>
								<!-- Category-->
								<section id="categorygrid">
									<table id="productlist" Style=" display: inline; width: -1px !important"  >
										<thead>
											<tr>
												<th style="border: none; display: none"></th>
												<th style="border: none; display: none"></th>
												<th style="border: none; display: none"></th>
												<th style="border: none; display: none"></th>
												<th style="border: none; display: none"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${productList}" var="product">
												<tr>
													<td><div class="span3">
															<a href="productDetail?productId=${product.productId}"><img
																alt=""
																src="<%=request.getContextPath()%>/images/product-base/catalogue/<c:out value='${product.imgPath}'/>"></a>
														</div></td>
													<td><div class="span6">
															<a href="productDetail?productId=${product.productId}"><b><c:out
																		value="${product.productName}" /></b></a> <br><b>Discounted Price :</b> $
															<c:out value="${product.pricePerUnit}" />
															<div class="productdiscrption">
																<c:out value="${product.productDesc}" />
																<br> <br>
																<div class="span6 productdiscrption">
																	<table class="table table-bordered">
																		<thead>
																			<tr>
																				<th style="border-bottom: none"><div
																						class="ratingstar">
																						<c:forEach begin="1" end="${product.rating}"
																							var="counter">
																							<i class="icon-star orange"> </i>
																						</c:forEach>
																						<c:forEach begin="1" end="${5 - product.rating}"
																							var="counter">
																							<i class="icon-star-empty"></i>
																						</c:forEach>
																					</div></th>
																				<th style="border-bottom: none"><button
																						onclick="javascript:addToCompare(${product.productId},${product.category.categoryId})"
																						class="btn btn-orange tooltip-test"
																						data-original-title="Compare">
																						<i class="icon-refresh icon-white"></i> Add to
																						Compare
																					</button></th>
																				<th style="border-bottom: none"><button
																						onclick="javascript:addTocart(${product.productId})"
																						class="btn btn-orange tooltip-test"
																						data-original-title="Cart">
																						<i class="icon-shopping-cart icon-white"></i> Add
																						to Cart
																					</button></th>
																			</tr>
																		</thead>
																	</table>
																</div>
															</div>

														</div></td>
													<td style="display: none">${product.category.categoryId}</td>
													<td style="display: none">${product.brand.brandId}</td>
													<td style="display: none">${product.pricePerUnit}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</section>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</section>
</div>

<script src="js/jquery.js"></script>
<script>
	var prodDataTable;
	var filterMap = {};
	var isLoadFirstTime = false;
	filterMap.brandId = [];
	$(function () {
		var selectedOption="<%= request.getParameter("option") %>";
		if(selectedOption){
			$("#dynamic_select option").each(function() {
				  if($(this).text() == selectedOption) {
				    $(this).attr('selected', 'selected');            
				  }                        
				});
		}
		prodDataTable = $('#productlist').DataTable();
		$('#productlist_filter').css({display: 'none'});
	  					
		$('#dynamic_select').on('change', function () {
	      var url = $(this).val(); // get selected value
	      if (url) { // require a URL
	          window.location = url; // redirect
	      }
	      return false;
	  	});
		
		$.fn.dataTable.ext.search.push(
			function( settings, data, dataIndex ) {
				var categoryId = data[2];
				var brandId = data[3];
				var price = parseFloat(data[4]);
				var categoryMatch = ((filterMap.categoryId != null && filterMap.categoryId != "" && (filterMap.categoryId == categoryId)) || (filterMap.categoryId == null));
				var brandMatch = ((filterMap.brandId != null && filterMap.brandId != "" && ($.inArray(brandId, filterMap.brandId) > -1)) || (filterMap.brandId.length == 0));
				var priceMatch = (((filterMap.from != null) && (filterMap.to != null && filterMap.to != "") && (price >= parseFloat(filterMap.from) && price <= parseFloat(filterMap.to))) || (filterMap.from == null));
				
				if( categoryMatch && brandMatch && priceMatch )	{
					return true;
				} else {
					return false;
				}
			}
		);
	  
	    $("#range").ionRangeSlider({
	        hide_min_max: true,
	        keyboard: true,
	        min: 0,
	        max: 5000,
	        from: 0,
	        to: 5000,
	        type: 'double',
	        step: 1,
	        prefix: "$",
	        grid: true,
			onFinish: function(data) {
				if(isLoadFirstTime) {
					filterMap.from = data.from;
					filterMap.to = data.to;
					$('.priceSpan').remove();
					$('#divIcons').append('<span style="padding-left:10px" class="priceSpan" onclick="removePrice(this)">Price: '+filterMap.from+'-'+filterMap.to+'<img class="removeFilter" src="<%=request.getContextPath()%>/img/close.png" alt="X"/></span>');
					prodDataTable.draw();
				}
				isLoadFirstTime = true;
			}
	    });
	});

	function filterCategory(categoryId, catergoryName) {
		filterMap.categoryId = categoryId;
		$('.categorySpan').remove();
		$('#divIcons').append('<span style="padding-left:10px" class="categorySpan" onclick="removeCategory(this)">Category: '+catergoryName+'<img class="removeFilter" src="<%=request.getContextPath()%>/img/close.png" alt="X"/></span>');
		prodDataTable.draw();
	}
	
	function filterBrand(brandId, brandObject) {

		if($(brandObject).is(":checked")) {
			filterMap.brandId.push(brandId+'');
        } else {
			filterMap.brandId = $.grep(filterMap.brandId, function(value) {
        	  return value != brandId;
        	});
        }
				
		prodDataTable.draw();
	}
	function removeCategory(currentObject) {
		filterMap.categoryId = null;
		$(currentObject).remove();
		prodDataTable.draw();
	}
	function removePrice(currentObject) {
		$(currentObject).remove();
		filterMap.from = null;
		filterMap.to = null;
		prodDataTable.draw();
		var slider = $("#range").data("ionRangeSlider");

		// Call sliders update method with any params
		slider.update({
			min: 0,
			max: 5000,
			from: 0,
			to: 5000,
		});
	}	
</script>