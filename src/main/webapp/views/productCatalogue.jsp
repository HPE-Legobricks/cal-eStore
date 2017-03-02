<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach var="productCatalogue" items="${catalogueMap}">
	<section id="${productCatalogue.key.styleClass}" class="row mt40">
		<div class="container">
			<div style="float: right; margin-right: 90px; margin-bottom: 1.8em">
				<a href="productList?productTypeCode=${productCatalogue.key.name}&OrderBy=&Order="><button class="comparebtn" style="z-index: 2; position: absolute">
					View All</button></a>
			</div>
			<h1 class="heading1">
				<span class="maintext">${productCatalogue.key.name}</span>
			</h1>
			<div class="${productCatalogue.key.styleClass}carousalwrap">
				<ul class="thumbnails"
					id="${productCatalogue.key.styleClass}carousal">
					<c:forEach var="product" items="${productCatalogue.value}">
						<li class="span3"><p title="${product.productName}">
								<a class="prdocutname prodtruncate" alt=""
									href="productDetail?productId=${product.productId}">
									${product.productName}</a>
							</p>
							<div class="thumbnail">
								<p title="${product.productName}">
									<a href="productDetail?productId=${product.productId}"><img
										alt=""
										src="<%=request.getContextPath()%>/images/product-base/catalogue/${product.imgPath}"></a>
								</p>
								<div class="">
									<!-- <button data-original-title="Product Detail">
										class="btn btn-orange btn-small tooltip-test">
										<i class="icon-list icon-white"></i>
									</button> -->
								</div>
								<div class="price">
									<div class="pricenew">
										<fmt:setLocale value="en_US"/>
										<fmt:formatNumber var="pricePerUnit" type="currency"
											minFractionDigits="2" maxFractionDigits="2"
											value="${product.pricePerUnit}" />
										${pricePerUnit}
									</div>
									<div class="ratingstar">
										<i class="icon-star orange"></i><i class="icon-star orange"></i><i
											class="icon-star orange"></i><i class="icon-star-empty"></i><i
											class="icon-star-empty"></i>
									</div>
								</div>
								<div>
									<a 
									href="javascript:addTocart(${product.productId})" 
									class="btn btn-orange btn-small  addtocartbutton"> Add
										to Cart</a>
								</div>
								<a
									href="javascript:addToCompare(${product.productId},${product.category.categoryId})"
									class="btn btn-orange btn-small  addtocartbutton prodisplaybtn">Compare</a>
							</div></li>
					</c:forEach>
				</ul>
				<div class="clearfix"></div>
				<a id="prev${productCatalogue.key.styleClass}" class="prev" href="#"><i
					class="icon-chevron-left"></i></a> <a
					id="next${productCatalogue.key.styleClass}" class="next" href="#"><i
					class="icon-chevron-right"></i></a>
			</div>
		</div>
	</section>
	<div style="clear: both"></div>
</c:forEach>
