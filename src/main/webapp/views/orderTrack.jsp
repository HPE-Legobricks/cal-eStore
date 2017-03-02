<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="maincontainer">
	<div class="container">
		<!--  breadcrumb -->
		<!-- <ul class="breadcrumb">
			<li><a href="productCatalogue">Home</a> <span class="divider">/</span></li>
			<li class="active">Order Track</li>
		</ul> -->
		<h1 class="heading1">
			<span class="maintext"> <i class="icon-tags"></i> Track Orders
			</span>
		</h1>

		<div id="content">
			<div class="manufacturer-list">
				<div class="manufacturer-heading">
					Current Order :<a id="A"></a>
				</div>
				<div>${latestOrder.orderId}</div>
			</div>
			<div>
				<!-- widget content -->
				<div class="widget-body">
					<div>
						<form id="wizard-1" novalidate>
							<div id="bootstrap-wizard-1" class="col-sm-12">
								<div class="form-bootstrapWizard">
									<ul class="bootstrapWizard form-wizard">
										<c:choose>
											<c:when test="${latestOrder.status.statusName == 'Ordered'}">
												<li data-target="#step1" class="active"><a
													data-toggle=""> <span class="step active">1</span> <span
														class="title active ">Ordered</span>
												</a></li>
											</c:when>
											<c:otherwise>
												<li class="" data-target="#step1"><a data-toggle="tab"
													class="deactive"> <span class="step">1</span> <span
														class="title">Ordered</span>
												</a></li>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${latestOrder.status.statusName == 'Processing'}">
												<li data-target="#step3" class="active"><a
													data-toggle=""> <span class="step active">2</span> <span
														class="title active ">Processing</span>
												</a></li>
											</c:when>
											<c:otherwise>
												<li data-target=""><a data-toggle="" class="deactive">
														<span class="step">2</span> <span class="title">Processing</span>
												</a></li>
											</c:otherwise>
										</c:choose>


										<c:choose>
											<c:when test="${latestOrder.status.statusName =='Shipped'}">
												<li data-target="#step3" class="active"><a
													data-toggle="tab"> <span class="step active">3</span> <span
														class="title active ">Shipped</span>
												</a></li>
											</c:when>
											<c:otherwise>
												<li data-target=""><a data-toggle="" class=""> <span
														class="step">3</span> <span class="title deactive">Shipped</span>
												</a></li>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${latestOrder.status.statusName =='Delivered'}">
												<li data-target="#step3" class="active"><a
													data-toggle="tab"> <span class="step active">4</span> <span
														class="title active ">Delivered</span>
												</a></li>
											</c:when>
											<c:otherwise>
												<li data-target=""><a data-toggle="" class=""> <span
														class="step">4</span> <span class="title ">Delivered</span>
												</a></li>
											</c:otherwise>
										</c:choose>


									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="tab-content">

									<c:choose>
										<c:when test="${latestOrder.status.statusName =='Ordered'}">
											<div class="tab-pane active" id="tab1">
												<br>
												<h4>Order Tracking</h4>
												<div style="">
													<a class="button pull-right" href="cancelOrder?orderId=${latestOrder.orderId}"
														class="btn btn-primary"> Cancel Order </a>
												</div>
											</div>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${latestOrder.status.statusName =='Processing'}">
											<div class="tab-pane active" id="">
												<br>
												<h4>Order Tracking</h4>
												<div style="float: right">
													<a href="cancelOrder?orderId=${latestOrder.orderId}"
														class="btn btn-primary"> Cancel Order </a>
												</div>
											</div>
										</c:when>
										<c:otherwise>

											<c:choose>
												<c:when test="${latestOrder.status.statusName =='Cancelled'}">
													<div class="tab-pane active" id="tab1">
														<br>
														<h4></h4>
														<div style="float: right">
															<a class="button disabled" title=" Order Status">Cancelled</a>
														</div>
													</div>
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose>

										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${latestOrder.status.statusName =='Shipped'}">

											<h3>Order can't be cancelled</h3>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${latestOrder.status.statusName =='Delivered'}">
											<h3>Order cant be cancelled</h3>

											<div>
												<br>
												<h3>Order can't be cancelled</h3>
											</div>
										</c:when>
										<c:otherwise>

										</c:otherwise>
									</c:choose>
								</div>
							</div>
					</div>
					</form>
				</div>
			</div>
			<!-- end widget content -->
		</div>





		<div id="content2">
			<div class="manufacturer-list">
				<div class="manufacturer-heading">
					Order History<a id="b"></a>
				</div>
				<div>
					<table class="table table-striped table-bordered compare">
						<tbody>
							<tr>
								<th width="10%" class="Productdisc">Order ID</th>
								<th width="40%" class="Productdisc">Items</th>
								<th width="20%" class="Productdisc">Status</th>
								<th width="30%" class="Productdisc">Action</th>
							</tr>
							<c:forEach var="purchaseOrder" items="${poList}">
								<tr>
									<td class="Productdisc">${purchaseOrder.orderId}</td>
									<td><c:forEach var="productOrder"
											items="${purchaseOrder.productOrders}">
										<li>${productOrder.product.productName}</li>
										</c:forEach></td>
									<td>${purchaseOrder.status.statusName}</td>
									<c:choose>
										<c:when
											test="${purchaseOrder.status.statusName =='Cancelled'}">
											<td><a class="button disabled pull-right">Cancelled</a></td>
										</c:when>
										<c:when
											test="${purchaseOrder.status.statusName =='Shipped'}">
											<td><a class="button disabled pull-right">Cancel Order</a></td>
										</c:when>
										<c:when
											test="${purchaseOrder.status.statusName =='Delivered'}">
											<td><a class="button disabled pull-right">Cancel Order</a></td>
										</c:when>
										<c:otherwise>
											<td><a class="button pull-right"
												href="cancelOrder?orderId=${purchaseOrder.orderId}">
													Cancel Order </a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
