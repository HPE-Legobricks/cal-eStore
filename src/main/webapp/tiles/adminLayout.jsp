<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/temp.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Dhaval">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/media.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/fonts/fonts.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/font-awesome.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/alert.css">
<!-- Favicons
================================================== -->
<!-- 128x128 -->
<link rel="shortcut icon" type="image/ico"
	href="images/favicons/favicon.ico">
<link rel="icon" type="image/png" href="images/favicons/favicon.png">

<link rel="icon" type="image/png" sizes="192x192"
	href="images/favicons/favicon-192.png">

<!-- 57x57 (precomposed) for iPhone 3GS, pre-2011 iPod Touch and older Android devices -->
<link rel="apple-touch-icon-precomposed"
	href="images/favicons/favicon-57.png">
<!-- 72x72 (precomposed) for 1st generation iPad, iPad 2 and iPad mini -->
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/favicons/favicon-72.png">
<!-- 114x114 (precomposed) for iPhone 4, 4S, 5 and post-2011 iPod Touch -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/favicons/favicon-114.png">
<!-- 144x144 (precomposed) for iPad 3rd and 4th generation -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/favicons/favicon-144.png">
<!-- Favicons
================================================== -->

</head>

<body>

	<header>
		<tiles:insertAttribute name="header" ignore="true" />
		<tiles:insertAttribute name="nav" ignore="true" />
	</header>

	<div class="main-section" id="main">
		<!-- Body inserted here -->
		<tiles:insertAttribute name="body" ignore="true" />
	</div>

	<footer id="footer">
		<!-- Footer -->
		<tiles:insertAttribute name="footer" ignore="true" />
	</footer>

	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>


</body>
</html>
