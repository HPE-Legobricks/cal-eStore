
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

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
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses' ], [ '2004', 1000, 400 ],
				[ '2005', 1170, 460 ], [ '2006', 660, 1120 ],
				[ '2007', 1030, 540 ] ]);

		var options = {
			hAxis : {
				title : 'Year'
			}
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div1'));
		chart.draw(data, options);
	}

	//chart-2

	function drawChart2() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses' ], [ '2013', 1000, 400 ],
				[ '2014', 1170, 460 ], [ '2015', 660, 1120 ],
				[ '2016', 1030, 540 ] ]);

		var options = {
			hAxis : {
				title : 'Year'
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
				[ 'Task', 'Hours per Day' ], [ 'Work', 11 ], [ 'Eat', 2 ],
				[ 'Commute', 2 ], [ 'Watch TV', 2 ], [ 'Sleep', 7 ] ]);

		var options = {
			title : 'My Daily Activities'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}

	//chart-4

	function drawChart4() {

		var data = new google.visualization.DataTable();
		data.addColumn('number', 'Days');
		data.addColumn('number', 'Department A');
		data.addColumn('number', 'Department B');
		data.addColumn('number', 'Department C');

		data.addRows([ [ 1, 100, 90, 70 ], [ 2, 110, 95, 75 ],
				[ 3, 115, 100, 82 ], [ 4, 120, 110, 90 ], [ 5, 140, 130, 110 ],
				[ 6, 115, 95, 80 ], [ 7, 100, 92, 75 ], [ 8, 123, 111, 92 ],
				[ 9, 130, 120, 100 ], [ 10, 128, 120, 112 ],
				[ 11, 135, 125, 115 ], [ 12, 128, 120, 112 ],
				[ 13, 128, 120, 112 ], [ 14, 135, 125, 115 ],
				[ 15, 128, 120, 112 ], [ 16, 128, 120, 112 ],
				[ 17, 145, 130, 120 ] ]);

		var options = {

			axes : {
				x : {
					0 : {
						side : 'bottom'
					}
				}
			}
		};

		var chart = new google.charts.Line(document
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
						class="info-box-number">#1000</span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i
					class="fa fa-shopping-cart"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Open Orders</span> <span
						class="info-box-number">#1000</span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i class="fa fa-dollar"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Total Cost</span> <span
						class="info-box-number">$1000.00</span>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="info-box">
				<span class="info-box-icon bg-blue"><i
					class="fa fa-arrow-circle-up"></i></span>
				<div class="info-box-content">
					<span class="info-box-text">Highest Cost Dept</span> <span
						class="info-box-number">Abc</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="box">
				<h4>Chart-1</h4>
				<div id="chart_div1" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="box">
				<h4>Chart-2</h4>
				<div id="chart_div2" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="box">
				<h4>Chart-3</h4>
				<div id="piechart" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="box">
				<h4>Chart-4</h4>
				<div id="chart_div4" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
	</div>
</div>
