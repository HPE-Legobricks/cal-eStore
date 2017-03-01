<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
.autocomplete-suggestion { padding: 5px 5px; white-space: nowrap; overflow: hidden; font-size:20px; font-family: "Source Sans Pro", "Helvetica Neue", "Helvetica", "Roboto", "Arial", sans-serif}
.autocomplete-selected { background: #F0F0F0; }
.autocomplete-suggestions strong { font-weight: bold; color: #3399FF; font-family: "Source Sans Pro", "Helvetica Neue", "Helvetica", "Roboto", "Arial", sans-serif}
</style>


<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="js/jquery.autocomplete.min.js" type="text/javascript"></script>

<script>


$(document).ready(function() {
	
	
	$( function() {
	    $( document ).tooltip();
	  } );
	
	$('#fname-id').bind('keyup blur',function(){ 
	    var node = $(this);
	    node.val(node.val().replace(/[^a-zA-Z0-9 ]/g,'') ); }
	);
	
	$('#lname-id').bind('keyup blur',function(){ 
	    var node = $(this);
	    node.val(node.val().replace(/[^a-zA-Z0-9 ]/g,'') ); }
	);
	
	$('#addr1-id').bind('keyup blur',function(){ 
	    var node = $(this);
	    node.val(node.val().replace(/[^a-zA-Z0-9 ]/g,'') ); }
	);
	
	$('#addr2-id').bind('keyup blur',function(){ 
	    var node = $(this);
	    node.val(node.val().replace(/[^a-zA-Z0-9 ]/g,'') ); }
	);
	

	$('#dept-id-search').autocomplete({
		
	    serviceUrl: '${pageContext.request.contextPath}/getDepartments',
	    paramName: "tagName",
	    delimiter: ",",
	   transformResult: function(response) {
		   
	    return {
	    	
	      suggestions: $.map($.parseJSON(response), function(item) {
	    	  
	          return { value: item.departmentName, data: item.departmentId };
	       })

	     };

	        }
	 });

});
</script>
	
	
<div class = "previewprofile col-lg-4 col-centered">
<!----------First Accordian------>
<form:form id="profileform" modelAttribute="user" action="${pageContext.request.contextPath}/registernewuser" method="POST" cssClass="usa-form">
<ul class="usa-accordion-bordered">
    <li>
      <button class="usa-accordion-button"
        aria-expanded="true" aria-controls="amendment-b-1">
        Your Personal Details
      </button>
      <div id="amendment-b-1" class="usa-accordion-content">

    <fieldset>
      <legend>Profile</legend>
		
		<!------Error message----->
	    <c:if test="${!empty param.auth}">
	       <div class="usa-alert usa-alert-error" role="alert">
	    		<div class="usa-alert-body">
	      		<h3 class="usa-alert-heading">Error</h3>
	      		<p class="usa-alert-text">${param.auth}</p>
	    	</div>
	    	</div>
	    </c:if>
	    
	    
	    
	    <!-- success -->
    	<c:if test="${!empty param.success}">
	        <div class="usa-alert usa-alert-success">
			    <div class="usa-alert-body">
			      <h3 class="usa-alert-heading">Success</h3>
			      <p class="usa-alert-text">${param.success}</p>
			    </div>
		   </div>
	    </c:if>
    
		<!-- Spring error messages. -->
		<spring:hasBindErrors name="user">
			 <div class="usa-alert usa-alert-error" role="alert">
	    		<div class="usa-alert-body">
		      		<h3 class="usa-alert-heading">Error</h3>
		      		<p class="usa-alert-text">
		      			
		      			<c:forEach var="error" items="${errors.allErrors}">
		      				<spring:message message="${error}" />
		      				<br/>
		      			</c:forEach>
		      		</p>
	    		</div>
	    	</div>
    	</spring:hasBindErrors>
    	
      <label for="firstName" class="usa-input-required">First name</label>
      <form:input id="fname-id" path="firstName" cssClass="usa-input-required" aria-required="true" />

      <label for="middle-name" class="usa-input-required">Last name</label>
      <form:input id="lname-id" path="lastName" cssClass="usa-input-required" aria-required="true" />
      
      
      <label for="dept-name" class="usa-input-required">Department name</label>
      <form:input id="dept-id-search" path="department.departmentName" cssClass="usa-input-required" aria-required="true" />

	  <label for="input-type-text" class="usa-input-required">Phone</label>
	  <form:input path="mobileNumber" cssClass="usa-input-required" aria-required="true" maxlength="12" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')"/>
  
      <label for="input-type-text" class="usa-input-required">Email</label>
      <form:input id="eml-id" path="emailId" cssClass="input-email-text" aria-required="true" />
    
      <label for="input-type-text" class="usa-input-required">Password</label>
  	  <form:password id="pwd-id" path="password" title="Password should ateast contain one capital letter, one number and a special character."/>
  	  
      <label for="input-type-text" class="usa-input-required">Confirm Password</label>
 	  <form:password path="confirmPassword"/>
  		
  	  <!-- DO NOT REMOVE -->	
  	  <input id="email" name="input-type-text" type="text" required="required" aria-required="false" hidden="true">
  
    </fieldset>
  </div>
    </li>
  <!----------Second Accordian------>
    <li>
      <button class="usa-accordion-button"
        aria-controls="amendment-b-2">
        Your Address
      </button>
      <div id="amendment-b-2" class="usa-accordion-content">
    <fieldset>
      <legend>Mailing address</legend>
      <label for="mailing-address-1" class="usa-input-required">Street address 1</label>
      <form:input id="addr1-id" path="address.addressLine1"/>

      <label for="mailing-address-2">Street address 2 <span class="usa-additional_text">(Optional)</span></label>
      <form:input id="addr2-id" path="address.addressLine2"/>

      <div>
        <div class="usa-input-grid usa-input-grid-medium">
          <label for="city" class="usa-input-required">State</label>
		 <form:select path="address.state">
            <form:option value="" label="Select" />
            <form:options items="${city}" />
         </form:select>
          
        </div>

        <div class="usa-input-grid usa-input-grid-small">
          <label for="state" class="usa-input-required">City</label>
          	<form:input path="address.city"/>
        </div>
      </div>

      <label for="zip" class="usa-input-required">ZIP</label>
      <!-- The example below includes the `data-politespace` attribute. This initializes Poltiespace to work with the zip code input. -->
      <form:input path="address.zipCode" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')" maxlength="10"/>
    </fieldset>
      </div>
    </li>
    <!----------Third Accordian------>
    <li>
      <button class="usa-accordion-button"
        aria-controls="amendment-b-4">
        Notification
      </button>
      <div id="amendment-b-4" class="usa-accordion-content">
    <fieldset>
      <legend>Receive Notification</legend>
    <ul class="usa-unstyled-list">
    
      <li>
        <input id="douglass" type="checkbox" name="emailChecked" value="Y">
        <label for="douglass">Email</label>
      </li>

    </ul>
      
    </fieldset>
      </div>
    </li>
    </ul>
        <button id="submit-btn-id" class=" bg-primary" formnovalidate="formnovalidate">Submit</button>
      </form:form>
</div>
