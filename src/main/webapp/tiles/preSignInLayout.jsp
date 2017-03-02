<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<!--[if lt IE 9]><html class="lt-ie9"><![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/uswds.min.css">

<!-- Favicons
================================================== -->
<!-- 128x128 -->
<link rel="shortcut icon" type="image/ico" href="img/favicons/favicon.ico">
<link rel="icon" type="image/png" href="img/favicons/favicon.png">

<!-- 192x192, as recommended for Android
  http://updates.html5rocks.com/2014/11/Support-for-theme-color-in-Chrome-39-for-Android
  -->
<link rel="icon" type="image/png" sizes="192x192" href="img/favicons/favicon-192.png">

<!-- 57x57 (precomposed) for iPhone 3GS, pre-2011 iPod Touch and older Android devices -->
<link rel="apple-touch-icon-precomposed" href="img/favicons/favicon-57.png">
<!-- 72x72 (precomposed) for 1st generation iPad, iPad 2 and iPad mini -->
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/favicons/favicon-72.png">
<!-- 114x114 (precomposed) for iPhone 4, 4S, 5 and post-2011 iPod Touch -->
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/favicons/favicon-114.png">
<!-- 144x144 (precomposed) for iPad 3rd and 4th generation -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/favicons/favicon-144.png">
<!-- Favicons
================================================== -->
</head>
<body>

<!--US compliance-->
<div class="container-fluid">
  <div class="row">
    <tiles:insertAttribute name="header" />
  </div>
</div>
<!--US compliance End-->
<div class="clear"></div>
<!-- Logo image place --> 
<!-- Logo image ends here --> 
    <div class="container"><img src="<%=request.getContextPath()%>/images/logo.png" class="img-responsive img-center" alt="Placeholder image"> </div>
<!--Login Form strat-->
<!-- <div class="container"> -->
<!--   <div class="row"> -->
    <tiles:insertAttribute name="body" />
<!--   </div> -->
  <!-- </div>--> 
<br>
<!--Form ends here-->
<footer class="container-fluid text-center">
  <p>(800) CALL-GOVT | <a href="mailto:ADPQ@state.ca.gov">ADPQ@state.ca.gov</a></p>
</footer>
<script src="<%=request.getContextPath()%>/js/uswds.min.js"></script>
</body>
</html>
