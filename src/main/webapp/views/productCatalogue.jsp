<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>

  function addToCompare(productId) {
	  //alert('hello' + productId);
	  $.ajax({
			type: "GET",
			url: "http://localhost:8080/calEStore/markForComparision?productId="+productId,
			cache:false,
			success: function (data) {
				alert('*** Product marked for comparision ***');
				console.log("success");
			},
			error: function (xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
				console.log("error");
				console.log(xhr.status);
				console.log(thrownError);
			}
		}); 
  }

</script>

<c:forEach var="productCatalogue" items="${catalogueMap}">
	<section id="${productCatalogue.key.styleClass}" class="row mt40">
		<div class="container">
			<div style="float: right; margin-right: 90px; margin-bottom: 1.8em">
				<button class="comparebtn" style="z-index: 2; position: absolute">
					<a href="productList?productTypeCode=${productCatalogue.key.name}&OrderBy=productName">View All</a></button>
			</div>
			<h1 class="heading1">
				<span class="maintext">${productCatalogue.key.name}</span>
			</h1>
			<div class="${productCatalogue.key.styleClass}carousalwrap">
				<ul class="thumbnails" id="${productCatalogue.key.styleClass}carousal">
					<c:forEach var="product" items="${productCatalogue.value}">
						<li class="span3"><p title="${product.productName}"><a class="prdocutname prodtruncate" alt="" href="productDetail?productId=${product.productId}">
								${product.productName}</a></p>
							<div class="thumbnail">
								<p title="${product.productName}"><a href="productDetail?productId=${product.productId}"><img alt=""
									src="<%=request.getContextPath()%>/images/product-base/catalogue/${product.imgPath}"></a></p>
								<div class="shortlinks">
									<button data-original-title="Product Detail"
										class="btn btn-orange btn-small tooltip-test">
										<i class="icon-list icon-white"></i>
									</button>
								</div>
								<div class="price">
									<div class="pricenew">$&nbsp;${product.pricePerUnit}</div>
									<div class="ratingstar">
										<i class="icon-star orange"></i><i class="icon-star orange"></i><i class="icon-star orange"></i><i class="icon-star-empty"></i><i class="icon-star-empty"></i>
									</div>
								</div>
								<div>
									<a class="btn btn-orange btn-small  addtocartbutton"> Add
										to Cart</a>
								</div>
								<a href="javascript:addToCompare(${product.productId})"
									class="btn btn-orange btn-small  addtocartbutton prodisplaybtn">Compare</a>
							</div>
						</li>
					</c:forEach>
				</ul>
				<div class="clearfix"></div>
				<a id="prev${productCatalogue.key.styleClass}" class="prev" href="#"><i
					class="icon-chevron-left"></i></a> <a id="next${productCatalogue.key.styleClass}" class="next"
					href="#"><i class="icon-chevron-right"></i></a>
			</div>
		</div>
	</section>
	<div style="clear: both"></div>
</c:forEach>
