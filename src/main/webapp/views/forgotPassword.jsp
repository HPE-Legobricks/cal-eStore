<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="col-lg-4 col-centered"> 
<!--Language Option-->
    <div class="btn-group" style="margin-left:20.0em">
      </div>
              <!--Language Option End-->

      <!--<div class="preview ">-->
      <form class="usa-form col-centered" action="${pageContext.request.contextPath}/sendpasswordmail">
        <fieldset>
          <legend class="usa-drop_text">Forgot Password</legend>
          <span>Please enter your Email Id</span>
          
          
          	
    <!------Success message----->
	<c:if test="${'exception' eq param.problem}">
	     <div class="usa-alert usa-alert-error" role="alert">
    		<div class="usa-alert-body">
      		<h3 class="usa-alert-heading">Error</h3>
      		<p class="usa-alert-text">Problem occurred.</p>
    		</div>
    	</div>
	</c:if>
	
	
	<!------Success message----->
	<c:if test="${'true' eq param.passed}">
	   <div class="usa-alert usa-alert-success">
	    <div class="usa-alert-body">
	      <h3 class="usa-alert-heading">Success</h3>
	      <p class="usa-alert-text">Your new password has been sent to you via email.</p>
	    </div>
	   </div>
	</c:if>
    
    <!------Error message----->
    <c:if test="${'false' eq param.failed}">
       <div class="usa-alert usa-alert-error" role="alert">
    		<div class="usa-alert-body">
      		<h3 class="usa-alert-heading">Error</h3>
      		<p class="usa-alert-text">Email id that you entered is invalid.</p>
    	</div>
    	</div>
    </c:if>
    
      <label for="password">Email Id</label>
      <input id="password" name="password" type="text" aria-describedby="validation_list" class="js-validate_password" data-validate-length=".{8,}" data-validate-uppercase="[A-Z]" data-validate-numerical="\d" data-validation-element="#validation_list">
		<input type="submit" value="Send password">
        </fieldset>
      </form>
    </div>