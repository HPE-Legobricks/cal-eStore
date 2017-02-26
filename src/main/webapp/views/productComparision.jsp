<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="product">
	<div class="container">
		<!-- Compare -->
		<div class="row">
			<!-- Compare Products-->
			<div class="span12">
				<h1 class="heading1">
					<span class="maintext"> <i class="icon-refresh"></i> Compare
						Products
					</span>
				</h1>
				<div class="cart-info">
					<table class="table table-striped table-bordered compare">
						<tbody>
							<tr>
								<td>&nbsp;</td>
								<c:forEach var="productName"
									items="${productComparisionDM.productNames}">
									<td class="name"><b>${productName}</b></td>
								</c:forEach>
							</tr>
							<c:forEach var="aspectDM"
								items="${productComparisionDM.aspectMap}">
								<tr>
									<td nowrap>${aspectDM.key}</td>
									<c:forEach var="aspectValue" items="${aspectDM.value}">
										<td><c:choose>
												<c:when test="${aspectDM.key == 'Rating'}">
													<div class="ratingstar">
														<c:forEach begin="1" end="${aspectValue}" var="counter">
															<i class="icon-star orange"> </i>
														</c:forEach>
														<c:forEach begin="1" end="${5 - aspectValue}"
															var="counter">
															<i class="icon-star-empty"></i>
														</c:forEach>
													</div>
												</c:when>
												<c:when test="${aspectDM.key == 'Image'}">
													<img
														src="<%=request.getContextPath()%>/images/product-base/catalogue/${aspectValue}">
												</c:when>
												<c:otherwise>
											        ${aspectValue}
											    </c:otherwise>
											</c:choose></td>
									</c:forEach>
								</tr>
							</c:forEach>

							<tr>
								<td>&nbsp;</td>
								<c:forEach var="productId"
									items="${productComparisionDM.productIds}">
									<th style="border-bottom: none"><button
											onclick="javascript:addTocart(${productId})"
											class="btn btn-orange tooltip-test"
											data-original-title="Cart">
											<i class="icon-shopping-cart icon-white"></i> Add to Cart
										</button></th>
								</c:forEach>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
