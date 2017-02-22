<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Details</title>
</head>
<body>
<div id="maincontainer">
  <section id="product">
    <div class="container"> 
      <!--  breadcrumb -->
      <ul class="breadcrumb">
        <li> <a href="#">Home</a> <span class="divider">/</span> </li>
        <li class="active"> Shopping Cart</li>
      </ul>
      <h1 class="heading1"><span class="maintext"> <i class="icon-shopping-cart"></i> Shopping Cart</span></h1>
      <!-- Cart-->
      <div class="cart-info">
        <table class="table table-striped table-bordered">
          <tr>
            <th class="image">Image</th>
            <th class="name">Product Name</th>
            <th class="model">Model</th>
            <th class="quantity">Qty</th>
            <th class="total">Action</th>
            <th class="price">Unit Price</th>
            <th class="total">Total</th>
          </tr>
          <tr>
            <td class="image"><a href="#"><img title="product" alt="product" src="img/prodcut-40x40.jpg" height="50" width="50"></a></td>
            <td  class="name"><a href="#">HP</a></td>
            <td class="model">Purchased Product</td>
            <td class="quantity"><input type="text" size="1" value="1" name="quantity[40]" class="span1"></td>
            <td class="total"> <a href="#"><i class="tooltip-test font24 icon-remove-circle" data-original-title="Remove"> </i></a></td>
            <td class="price">$120.68</td>
            <td class="total">$120.68</td>
          </tr>
          <tr>
            <td class="image"><a href="#"><img title="product" alt="product" src="img/prodcut-40x40.jpg" height="50" width="50"></a></td>
            <td  class="name"><a href="#">HP</a></td>
            <td class="model">Purchased Product</td>
            <td class="quantity"><input type="text" size="1" value="1" name="quantity[40]" class="span1"></td>
            <td class="total"> <a href="#"><i class="tooltip-test font24 icon-remove-circle" data-original-title="Remove"> </i></a></td>
            <td class="price">$120.68</td>
            <td class="total">$120.68</td>
          </tr>
          <tr>
            <td class="image"><a href="#"><img title="product" alt="product" src="img/prodcut-40x40.jpg" height="50" width="50"></a></td>
            <td  class="name"><a href="#">HP</a></td>
            <td class="model">Purchased Product</td>
            <td class="quantity"><input type="text" size="1" value="1" name="quantity[40]" class="span1"></td>
            <td class="total"> <a href="#"><i class="tooltip-test font24 icon-remove-circle" data-original-title="Remove"> </i></a></td>
            <td class="price">$120.68</td>
            <td class="total">$120.68</td>
          </tr>
          <tr>
            <td class="image"><a href="#"><img title="product" alt="product" src="img/prodcut-40x40.jpg" height="50" width="50"></a></td>
            <td  class="name"><a href="#">Dell</a></td>
            <td class="model">Purchased Product</td>
            <td class="quantity"><input type="text" size="1" value="1" name="quantity[40]" class="span1"></td>
            <td class="total"><a href="#"><i class="tooltip-test font24 icon-remove-circle" data-original-title="Remove"> </i></a></td>
            <td class="price">$120.68</td>
            <td class="total">$120.68</td>
          </tr>
        </table>
      </div>
      <div >
        <section class="newcustomer">
          <h2 class="heading2">SHip to</h2>
          <div class="loginbox">
            <div class="controls">
              <textarea  class="required" rows="6" cols="40" id="message" name="messagee"></textarea>
            </div>
          </div>
        </section>
        <section class="returncustomer">
        <div>
          <h2 class="heading2">Change Address</h2>
          <div class="loginbox">
            <div class="controls">
              <textarea  class="required" rows="6" cols="40" id="message2" name="messagee1"></textarea>
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
            <table class="table table-striped table-bordered ">
              <tr>
                <td><span class="extra bold totalamout">Total :</span></td>
                <td><span class="bold totalamout">$150.28</span></td>
              </tr>
            </table>
            <input type="submit" value="Proceed to CheckOut" class="btn btn-orange pull-right mb10">
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</body>
</html>