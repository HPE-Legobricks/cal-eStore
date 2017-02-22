<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<div id="maincontainer">
  <section id="product">
    <div class="container"> 
      <!--  breadcrumb -->
      <ul class="breadcrumb">
        <li> <a href="#">Home</a> <span class="divider">/</span> </li>
        <li class="active">Product List View</li>
      </ul>
      <div class="row"> 
        <!-- Sidebar Start--> 
        <!-- Sidebar-->
        <aside class="span3"> 
          <!-- Category-->
          <div class="sidewidt">
            <h1 class="heading1"><span class="maintext"><em class="icon-th-list"></em> Categories</span></h1>
            <ul class="nav nav-list categories">
              <li> <a href="javascript:filterCategory(8)">Audio Devices</a></li>
              <li> <a href="javascript:filterCategory(1)">Battery</a> </li>
              <li> <a href="javascript:filterCategory(2)">Desktop</a> </li>
              <li> <a href="category.html">Hard Drive</a> </li>
              <li> <a href="category.html">Keyboard/Mouse</a> </li>
              <li> <a href="category.html">Laptop</a> </li>
              <li> <a href="category.html">Laptop</a> </li>
              <li> <a href="category.html">Monitor</a> </li>
              <li> <a href="category.html">Processor</a> </li>
              <li> <a href="category.html">RAM</a> </li>
            </ul>
          </div>
          <!-- Latest Product --> 
          
          <!--  Price -->
          <div class="sidewidt">
            <h1 class="heading1"><span class="maintext">Select by Price</span></h1>
            <section>
              <div style="position: relative; padding: 0px;">
                <div>
                  <input type="text" id="range" value="" name="range" />
                </div>
              </div>
            </section>
          </div>
          
          <!--  Brand -->
          
          <div class="sidewidt">
            <h1 class="heading1"><span class="maintext">Brand</span></h1>
            <section >
<div class="controls">
                  <label class="checkbox">
                    <input type="checkbox" value="option1" name="optionsCheckboxList1">
                    Dell </label>
                  <label class="checkbox">
                    <input type="checkbox" value="option2" name="optionsCheckboxList2">
                    HP </label>
                  <label class="checkbox">
                    <input type="checkbox" value="option3" name="optionsCheckboxList3">
                    ViewSonic </label>
                                      <label class="checkbox">

                                        <input type="checkbox" value="option4" name="optionsCheckboxList3">
                    NWN </label>
                  
             </div>
            </section>
          </div>
          <!--  Brand Ends -->
        </aside>
        
        <!-- Sidebar End--> 
        <!-- Category-->
        <div class="span9"> 
          <!-- Category Products-->
          <section id="category">
            <div class="row">
              <div class="span9"> 
                <!-- Sorting-->
                <div class="sorting well">
                  <form class=" form-inline pull-left">
                    Sort By :
                    <select class="span2" id = "dynamic_select">
                      <option value="">Default</option>
                      <option value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=productName">Name</option>
                      <option value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=pricePerUnit">Price</option>
                      <option value="productList?productTypeCode=<%= request.getParameter("productTypeCode") %>&OrderBy=rating">Rating </option>
                     </select>
                  
                  </form>
                  <div class="btn-group pull-right">
                    <button class="btn btn-orange" id="list"><i class="icon-th-list"></i> </button>
                    <button class="btn " id="grid"><i class="icon-th icon-white"></i></button>
                  </div>
                </div>
                <!-- Category-->
                <section id="categorygrid">
				<table id="productlist">
					<thead>
						<tr>
							<th style="border: none;display: none"></th>
							<th style="border: none;display: none"></th>
							<th style="border: none;display: none"></th>
							<th style="border: none;display: none"></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${productList}" var="product">
					<tr>
						<td><div class="span3"> <a href="#"><img alt="" src="<%=request.getContextPath()%>/images/product-base/catalogue/<c:out value='${product.imgPath}'/>"></a> </div></td>
						<td><div class="span6"> <a class="prdocutname" href="product.html"><b><c:out value="${product.productName}"/></b></a>
									<br>Price : $<c:out value="${product.pricePerUnit}"/>
                            <div class="productdiscrption"><c:out value="${product.productDesc}"/><br>
                              <br>
                              <div class="span6 productdiscrption" >
                                <table class="table table-bordered">
                                  <thead>
                                    <tr>
                                      <th><div class="ratingstar">
									  <c:forEach begin="1" end="${product.rating}" var="counter">
											   <i class="icon-star orange"> </i>
											</c:forEach>
									 <c:forEach begin="1" end="${5 - product.rating}" var="counter">
											   <i class="icon-star-empty"></i>
											</c:forEach>
									</div></th>
                                      <th><button class="btn btn-orange tooltip-test" data-original-title="Compare"> <i class="icon-refresh icon-white"></i> Add to Compare </button></th>
                                      <th><button class="btn btn-orange tooltip-test" data-original-title="Cart"> <i class="icon-shopping-cart icon-white"></i> Add to Cart</button></th>
                                    </tr>
                                  </thead>
                                </table>
                              </div>
                            </div>
 
                          </div>
						</td>            
						<td style="display: none">${product.category.categoryId}</td>
						<td style="display: none">${product.brand.brandId}</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
                </section>
              </div>
            </div>
          </section>
        </div>
      </div>
    </div>
  </section>
</div>
  
<script src="js/jquery.js"></script>
<script>
	var prodDataTable;
    $(function () {
		prodDataTable = $('#productlist').DataTable();
		$('#productlist_filter').css({display: 'none'});
	  					
		$('#dynamic_select').on('change', function () {
          var url = $(this).val(); // get selected value
          if (url) { // require a URL
              window.location = url; // redirect
          }
          return false;
      	});
	  
	    $("#range").ionRangeSlider({
            hide_min_max: true,
            keyboard: true,
            min: 0,
            max: 5000,
            from: 1000,
            to: 4000,
            type: 'double',
            step: 1,
            prefix: "$",
            grid: true
        });
    });
	
    function filterCategory(categoryId) {
		prodDataTable.columns( 2 ).search( categoryId ).draw();
	}
 
</script>