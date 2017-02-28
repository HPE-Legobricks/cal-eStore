<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<section id="product">
	<div class="container">
		<!-- Product Details-->
		<div class="row">
			<!-- Left Image-->
			<div class="span5">
				<ul class="thumbnails mainimage">
					<li class="span5"><a
						rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4"
						class="thumbnail cloud-zoom"
						href="<%=request.getContextPath()%>/images/product-base/detail/${product.imgPath}">
							<img alt=""
							src="<%=request.getContextPath()%>/images/product-base/detail/${product.imgPath}">
					</a></li>
				</ul>
				<span>Mouse move on Image to zoom</span>
			</div>
			<div class="row">
				<!-- Right Details-->
				<div class="span7">
					<div class="row">
						<div class="span7">
							<h1 class="productname">
								<span class="bgnone">${product.productName} </span>
							</h1>
							<div class="productprice">
								<div class="productpageprice">
									<h6>
										Contract Price:
										<fmt:setLocale value="en_US" />
										<fmt:formatNumber var="pricePerUnit" type="currency"
											minFractionDigits="2" maxFractionDigits="2"
											value="${product.pricePerUnit}" />
										<b>${pricePerUnit}</b>
									</h6>
								</div>
								<div class="productpageoldprice">
									<h6>
										List price :
										<fmt:formatNumber var="msrpPerUnit" type="currency"
											minFractionDigits="2" maxFractionDigits="2"
											value="${product.msrpPerUnit}" />
										<b>${msrpPerUnit}</b>
									</h6>
								</div>
							</div>
							<div class="controls"></div>
							<div class="control-group">
								<div class="controls">
									<h6>
										Discount: <b>${product.discPercent} %</b>
									</h6>
									<h6>Description:</h6>
									${product.productDesc}
								</div>
							</div>
							<div class="span6">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th><div class="ratingstar">
													<c:forEach begin="1" end="${product.rating}" var="counter">
														<i class="icon-star orange"> </i>
													</c:forEach>
													<c:forEach begin="1" end="${5 - product.rating}"
														var="counter">
														<i class="icon-star-empty"></i>
													</c:forEach>
												</div></th>
											<th><button
													onclick="javascript:addToCompare(${product.productId},${product.category.categoryId})"
													class="btn btn-orange tooltip-test"
													data-original-title="Compare">
													<i class="icon-refresh icon-white"></i> Add to Compare
												</button></th>
											<th><button
													onclick="javascript:addTocart(${product.productId})"
													class="btn btn-orange tooltip-test"
													data-original-title="Cart">
													<i class="icon-shopping-cart icon-white"></i> Add to Cart
												</button></th>
										</tr>
									</thead>
								</table>
							</div>

							<!-- Product Description tab & comments-->
							<div class="productdesc"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
