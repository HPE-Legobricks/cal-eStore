<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script>
	var weeklyReportData = [['Week'
	
	<c:forEach var="orderData" items="${orderDataMap}">
	,'${orderData.key}'
	</c:forEach>
	]

	<c:forEach var="weeklyOrderPerDept" items="${weeklyOrderPerDeptMap}">
		,['${weeklyOrderPerDept.key}'
		<c:forEach var="orderPerDept" items="${weeklyOrderPerDept.value}">
			,${orderPerDept.value}
		</c:forEach>
		]
	</c:forEach>
	];

	var cancelledReportData = [['Week'
	                     	
	                     	<c:forEach var="orderData" items="${cancelledDataMap}">
	                     	,'${orderData.key}'
	                     	</c:forEach>
	                     	]

	                     	<c:forEach var="cancelledOrderPerVendor" items="${cancelledOrdersPerVendorMap}">
	                     		,['${cancelledOrderPerVendor.key}'
	                     		<c:forEach var="orderPerVendor" items="${cancelledOrderPerVendor.value}">
	                     			,${orderPerVendor.value}
	                     		</c:forEach>
	                     		]
	                     	</c:forEach>
	                     	];

	var deliveredReportData = [['Week'
		                     	
		                     	<c:forEach var="orderData" items="${deliveredDataMap}">
		                     	,'${orderData.key}'
		                     	</c:forEach>
		                     	]

		                     	<c:forEach var="deliveredOrderPerVendor" items="${deliveredOrdersPerVendorMap}">
		                     		,['${deliveredOrderPerVendor.key}'
		                     		<c:forEach var="orderPerVendor" items="${deliveredOrderPerVendor.value}">
		                     			,${orderPerVendor.value}
		                     		</c:forEach>
		                     		]
		                     	</c:forEach>
		                     	];

</script>


<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart', 'line' ]
	});
	google.charts.setOnLoadCallback(drawChart1);
	google.charts.setOnLoadCallback(drawChart2);
	google.charts.setOnLoadCallback(drawChart3);
	google.charts.setOnLoadCallback(drawChart4);

	//chart-1

	function drawChart1() {

		var data = google.visualization.arrayToDataTable(weeklyReportData);

		var options = {
			hAxis : {
				title : 'Week'
			}
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div1'));
		chart.draw(data, options);
	}

	//chart-2

	function drawChart2() {

		var data = google.visualization.arrayToDataTable(cancelledReportData);

		var options = {
			hAxis : {
				title : 'Week'
			},
			vAxis : {
				minValue : 0
			}
		};

		var chart = new google.visualization.AreaChart(document
				.getElementById('chart_div2'));
		chart.draw(data, options);
	}

	//chart-3

	function drawChart3() {

		var data = google.visualization.arrayToDataTable([
				[ 'Inprocess', 'Shipped' ], [ 'Inprocess', ${orderStatusDm.ordersInprogress} ],
				[ 'Shipped', ${orderStatusDm.ordersShipped} ] ]);

		var options = {
			title : ''
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}

	//chart-4

	function drawChart4() {

		var data = google.visualization.arrayToDataTable(deliveredReportData);

		var options = {
			hAxis : {
				title : 'Week'
			},
			vAxis : {
				minValue : 0
			}
		};

		var chart = new google.visualization.AreaChart(document
				.getElementById('chart_div4'));
		chart.draw(data, options);

	}
</script>



<div class="container">
	<div class="row">
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i class="fa fa-desktop"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Total Orders</span> <span
						class="info-box-number">${orderStatistics.totalOrders}</span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i
					class="fa fa-shopping-cart"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Open Orders</span> <span
						class="info-box-number">${orderStatistics.openOrders}</span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i class="fa fa-dollar"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Total Cost</span> <span
						class="info-box-number"><fmt:formatNumber
							var="totalOrderCost" type="currency" minFractionDigits="2"
							maxFractionDigits="2" value="${orderStatistics.totalOrderCost}" />
						${totalOrderCost} </span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i
					class="fa fa-arrow-circle-up"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Highest Costly Dept</span> <span
						class="info-box-number">${orderStatistics.highSpendingDeptName}</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="box">
				<h4>Total Active Orders</h4>
				<div id="piechart" style="width: 100%; height: 250px;"></div>
			</div>

		</div>
		<div class="col-sm-6">
			<div class="box">
				<h4>Number of placed orders by departments</h4>
				<div id="chart_div1" style="width: 100%; height: 250px;"></div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="box">
				<h4>Number of cancelled orders by vendor</h4>
				<div id="chart_div2" style="width: 100%; height: 250px;"></div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="box">
				<h4>Number of delivered orders by vendor</h4>
				<div id="chart_div4" style="width: 100%; height: 250px;"></div>
			</div>
		</div>
	</div>
</div>
