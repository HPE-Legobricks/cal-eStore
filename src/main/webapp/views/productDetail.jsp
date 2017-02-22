<section id="product">
	<div class="container">
		<!-- Product Details-->
		<div class="row">
			<!-- Left Image-->
			<div class="span5">
				<ul class="thumbnails mainimage">
					<li class="span5"><a
						rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4"
						class="thumbnail cloud-zoom" href="img/product1big.jpg"> <img alt=""
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
								<span class="bgnone">
								    ${product.productName}
								</span>
							</h1>
							<div class="productprice">
								<div class="productpageprice">
									<span class="spiral"></span>$&nbsp;${product.pricePerUnit}
								</div>
								<div class="productpageoldprice">Old price : $&nbsp${product.msrpPerUnit}</div>
							</div>

							<div class="controls"></div>
							<div class="control-group">
								<div class="controls">

									<h6>Discount: <b>${product.discPercent} %</b></h6>
                                    ${product.productDesc}
								</div>
							</div>
							<div class="span6">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th><div class="ratingstar">
													<i class="icon-star orange"> </i><i
														class="icon-star orange"> </i><i class="icon-star"> </i> <i
														class="icon-star-empty"></i> <i class="icon-star-empty"></i>
												</div></th>
											<th><button class="btn btn-orange tooltip-test"
													data-original-title="Compare">
													<i class="icon-refresh icon-white"></i> Add to Compare
												</button></th>
											<th><button class="btn btn-orange tooltip-test"
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
