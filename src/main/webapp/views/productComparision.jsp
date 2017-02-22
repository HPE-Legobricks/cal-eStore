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
									<td class="name"><b><a href="#">${productName}</a></b></td>
								</c:forEach>
							</tr>
							<c:forEach var="aspectDM"
								items="${productComparisionDM.aspectMap}">
								<tr>
									<td nowrap>${aspectDM.key}</td>
									<c:forEach var="aspectValue" items="${aspectDM.value}">
										<td>${aspectValue}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
