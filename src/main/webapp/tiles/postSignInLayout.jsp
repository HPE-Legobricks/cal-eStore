<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/cloud-zoom.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/normalize.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/ion.rangeSlider.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/ion.rangeSlider.skinFlat.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/font-awesome.css"
	rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- fav -->
<!-- Favicons
================================================== -->
<!-- 128x128 -->
<link rel="shortcut icon" type="image/ico"
	href="img/favicons/favicon.ico">
<link rel="icon" type="image/png" href="img/favicons/favicon.png">

<!-- 192x192, as recommended for Android
  http://updates.html5rocks.com/2014/11/Support-for-theme-color-in-Chrome-39-for-Android
  -->
<link rel="icon" type="image/png" sizes="192x192"
	href="img/favicons/favicon-192.png">

<!-- 57x57 (precomposed) for iPhone 3GS, pre-2011 iPod Touch and older Android devices -->
<link rel="apple-touch-icon-precomposed"
	href="img/favicons/favicon-57.png">
<!-- 72x72 (precomposed) for 1st generation iPad, iPad 2 and iPad mini -->
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/favicons/favicon-72.png">
<!-- 114x114 (precomposed) for iPhone 4, 4S, 5 and post-2011 iPod Touch -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/favicons/favicon-114.png">
<!-- 144x144 (precomposed) for iPad 3rd and 4th generation -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/favicons/favicon-144.png">
<!-- Favicons
================================================== -->

<style>
#snackbar {
    visibility: hidden;
    min-width: 250px;
    margin-left: -125px;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 2px;
    padding: 16px;
    position: fixed;
    z-index: 1;
    left: 50%;
      top: 60px;
  
    font-size: 17px;
}

#snackbar.show {
    visibility: visible;
    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
    animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
    from {top: 0; opacity: 0;} 
    to {top: 60px; opacity: 1;}
}

@keyframes fadein {
    from {top: 0; opacity: 0;}
    to {top: 60px; opacity: 1;}
}

@-webkit-keyframes fadeout {
    from {top: 60px; opacity: 1;} 
    to {top: 0; opacity: 0;}
}

@keyframes fadeout {
    from {top: 60px; opacity: 1;}
    to {top: 0; opacity: 0;}
}
</style>


</head>
<body>
	<!-- Header Start -->
	<header>
		<tiles:insertAttribute name="header" ignore="true" />
		<tiles:insertAttribute name="nav" ignore="true" />
	</header>
	<!-- Header End -->
	<div id="maincontainer">
		<!--slyder style-->
		<!-- #region Jssor Slider Begin -->
		<!-- Generator: Jssor Slider Maker -->
		<!-- Source: http://www.jssor.com -->
		<!-- This code works without jquery library. -->
		<script src="js/jssor.slider-22.2.0.min.js"
			type="text/javascript"></script>
		<script type="text/javascript">
			jssor_1_slider_init = function() {

				var jssor_1_options = {
					$AutoPlay : true,
					$SlideDuration : 800,
					$SlideEasing : $Jease$.$OutQuint,
					$ArrowNavigatorOptions : {
						$Class : $JssorArrowNavigator$
					},
					$BulletNavigatorOptions : {
						$Class : $JssorBulletNavigator$
					}
				};

				var jssor_1_slider = new $JssorSlider$("jssor_1",
						jssor_1_options);

				/*responsive code begin*/
				/*you can remove responsive code if you don't want the slider scales while window resizing*/
				function ScaleSlider() {
					var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
					if (refSize) {
						refSize = Math.min(refSize, 1920);
						jssor_1_slider.$ScaleWidth(refSize);
					} else {
						window.setTimeout(ScaleSlider, 30);
					}
				}
				ScaleSlider();
				$Jssor$.$AddEvent(window, "load", ScaleSlider);
				$Jssor$.$AddEvent(window, "resize", ScaleSlider);
				$Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
				/*responsive code end*/
			};
		</script>
		<!-- Slider Start-->
		<tiles:insertAttribute name="slider" ignore="true" />
		<!-- Slider End-->

		<!-- Body inserted here -->
		<tiles:insertAttribute name="body" ignore="true" />

		<!-- Footer -->
		<tiles:insertAttribute name="footer" ignore="true" />

		<!-- javascript ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript"
			src="http://maps.google.com/maps/api/js?sensor=true"></script>
		<script src="js/jquery.js"></script>
		<script type="text/javascript"  src="js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.js"></script>
		<script src="js/respond.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script defer src="js/jquery.slider.js"></script>
		<script src="js/product.jquery.js" type="text/javascript"></script>
		<script src="js/cloud-zoom.1.0.2.js"></script>
		<!--<script  type="text/javascript" src="js/jquery.validate.js"></script> -->
		<script type="text/javascript"
			src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
		<script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
		<script type="text/javascript" src="js/jquery.touchSwipe.min.js"></script>
		<script type="text/javascript" src="js/jquery.gmap.js"></script>
		<script defer src="js/custom.js"></script>
		<script src="js/ion.rangeSlider.js"></script>
	</div>
</body>
<script>
$(document).ready(function() {
 	//$('#productlist').dataTable();
 });
</script>
</html>