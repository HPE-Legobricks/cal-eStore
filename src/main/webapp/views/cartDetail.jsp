<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function caltotal(s, Qtyval) {
		var rowind, rowcont, unitprice, rowtotal;
		var dsymb = "$";
		rowind = s.parentNode.parentNode.rowIndex;
		rowcont = document.getElementById("mytable").rows[rowind].cells;
		unitprice = rowcont[5].innerHTML.replace("$", "").replace("£","");
		
		
		rowtotal = Qtyval * unitprice;
		rowtotal = dsymb.concat(rowtotal);
		rowcont[6].innerHTML = rowtotal;
	}

	function totalcalculation() {
		var z, totalval = 0, tot = 0, dolsym = "$", rowsum = 0;
		/* var totval=0;
		 var tot=0;
		 dolsym="$";*/
		var tableindex = document.getElementById("mytable").rows.length;
		tableindex = tableindex - 1;
		for (i = 1; i <= tableindex; ++i) {
			z = document.getElementById("mytable").rows[i].cells;
			tot = z[6].innerHTML.replace("$", "").replace("£","");
			rowsum = parseInt(tot) + parseInt(rowsum);

		}
		totval = rowsum;
		rowsum = dolsym.concat(rowsum);
		document.getElementById("sumtotal").innerHTML = rowsum;
	}

	function myFunction(r) {

		var sumtotal1 = document.getElementById("sumtotal").innerHTML.replace(
				"$", "").replace("£","");
		var i = r.parentNode.parentNode.rowIndex;
		var x, currencyval, sumtext;
		var dollarsymb = '$';
		x = document.getElementById("mytable").rows[i].cells;//get all the contents of the row
		currencyval = x[6].innerHTML.replace("$", "").replace("£","");//To get only the currency value excluding dollar($) symbol
		alert(currencyval);
		sumtotal1 = sumtotal1 - currencyval;//subtraction from the total amount
		alert(parseFloat(sumtotal1).toFixed( 2 ));
		sumtext = dollarsymb.concat(sumtotal1);//append the dollar symbol
		document.getElementById("mytable").deleteRow(i);
		document.getElementById("sumtotal").innerHTML = sumtext;
		var rowscount = document.getElementById('mytable').rows.length;
		if (rowscount <= 1) {
			document.getElementById("proceedToCheckout").style.visibility = 'hidden';
			document.getElementById("sumtotal").innerHTML = 0.00;
		}
	}
	
	function checkForTables() {
		if (document.getElementById("mytable")) {
			var rowscount = document.getElementById('mytable').rows.length;
			if (rowscount <= 1) {
				document.getElementById("proceedToCheckout").style.visibility = 'hidden';
				document.getElementById("sumtotal").innerHTML = 0;
			}
		}
	}
	
	/* function removeFromCart(productId){
		alert('in cart');
	}
	 */
</script>
<body onload='checkForTables()'>
	<div id="maincontainer">
		<section id="product">
			<div class="container">
				<!--  breadcrumb -->
				<!-- <ul class="breadcrumb">
				<li><a href="productCatalogue">Home</a> <span class="divider">/</span></li>
				<li class="active">Shopping Cart</li>
			</ul> -->
				<h1 class="heading1">
					<span class="maintext"> <i class="icon-shopping-cart"></i>
						Shopping Cart
					</span>
				</h1>
				<!-- Cart-->
				<div class="cart-info">
					<table id="mytable" class="table table-striped table-bordered">
						<tr>
							<th class="image">Image</th>
							<th class="name">Product Name</th>
							<th class="model">Model</th>
							<th class="quantity">Qty</th>
							<th class="total">Action</th>
							<th class="price">Unit Price</th>
							<th class="total">Total</th>
						</tr>
						<c:forEach var="product" items="${productsInfo}">
							<tr>
								<td class="image"><a href="#"><img title="product"
										alt="product"
										src="<%=request.getContextPath()%>/images/product-base/catalogue/${product.imgPath}"
										height="50" width="50"></a></td>
								<td class="name"><a href="#">${product.productName}</a></td>
								<td class="model">${product.brand.brandName}</td>
								<td class="quantity"><input type="text"
									value=${cartItemsMap[product.productId] } name="quantity[40]"
									class="span1" class="span1"
									onkeyup="caltotal(this,this.value),totalcalculation();"></td>
								<td class="total"><a id="myLink" title="Click" href=""
									onclick="myFunction(this);removeFromCart(${product.productId});return false;"><i
										class="tooltip-test font24 icon-remove-circle"
										data-original-title="Remove"> </i></a></td>

								<td class="price"><fmt:setLocale value="en_US" /> <fmt:formatNumber
										var="pricePerUnit" type="currency" minFractionDigits="2"
										maxFractionDigits="2" value="${product.pricePerUnit}" />
									${pricePerUnit}</td>
								<td class="total pull right"><fmt:setLocale value="en_US" /> <fmt:formatNumber
										var="pricePerUnit" type="currency" minFractionDigits="2"
										maxFractionDigits="2" value="${product.pricePerUnit}" />
									${pricePerUnit}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<section class="newcustomer">
						<h2 class="heading2">SHip to</h2>
						<div class="loginbox">
							<div class="controls">
								<textarea class="required" rows="6" cols="40" id="message"
									name="messagee">${shipToAddress}</textarea>
							</div>
						</div>
					</section>
					<section class="returncustomer">
						<div>
							<h2 class="heading2">Bill to</h2>
							<div class="loginbox">
								<div class="controls">
									<p>
										Department Contract ID<strong> XXXX-XXXX-0123<strong>
									</p>
								</div>
							</div>
							<!--                <br>
                <a href="#" class="btn btn-orange">Update</a>-->
						</div>
					</section>
				</div>
				<div class="container">
					<div class="pull-right">
						<div class="span4 pull-right">
							<span class="extra bold totalamout">
								<table class="table table-striped table-bordered ">
									<tr>
										<td>Total:</span></td>
										<td id="sumtotal" style="text-align:right; float:right"><script>totalcalculation();</script></td>
									</tr>
								</table>
							</span> <input type="button" id="proceedToCheckout" name="button"
								value="Proceed to CheckOut"
								class="btn btn-orange pull-right mb10"
								onclick="location.href='orderConfirmation'"> <input
								type="submit" value="Continue Shopping"
								onclick="location.href='productCatalogue'"
								class="btn btn-orange pull-right mr10">
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>