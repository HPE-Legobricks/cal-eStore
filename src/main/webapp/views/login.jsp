<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="col-lg-4 col-centered">
	<!--Language Option-->
	<!-- <div class="btn-group" style="margin-left: 20.0em">
		<a href="javascript:void(0);">English</a> | <a
			href="javascript:void(0);">Spanish</a>
	</div> -->
	<!--Language Option End-->
	
	
	<!------Success message----->
	<c:if test="${'success' eq param.logout}">
	   <div class="usa-alert usa-alert-success">
	    <div class="usa-alert-body">
	      <h3 class="usa-alert-heading">Success</h3>
	      <p class="usa-alert-text">You have been logged out successfully.</p>
	    </div>
	   </div>
	</c:if>
    
    <!------Error message----->
    <c:if test="${'fail' eq param.auth}">
       <div class="usa-alert usa-alert-error" role="alert">
    		<div class="usa-alert-body">
      		<h3 class="usa-alert-heading">Error</h3>
      		<p class="usa-alert-text">Login failed due to invalid credentials.</p>
    	</div>
    	</div>
    </c:if>
	
	
	<!-- Mail Send - Create New profile. -->
	 <c:if test="${'success' eq param.mailsend}">
      <div class="usa-alert usa-alert-success">
	    <div class="usa-alert-body">
	      <h3 class="usa-alert-heading">Success</h3>
	      <p class="usa-alert-text">E-mail has been sent to the admin for an approval.</p>
	    </div>
	   </div>
    </c:if>
	
	
	<!--<div class="preview ">-->
	
	
	<form action="${pageContext.request.contextPath}/login" method="post" class="usa-form col-centered">
		<fieldset>
			<legend class="usa-drop_text">Sign in</legend>
			<span>or <a href="${pageContext.request.contextPath}/userregistration">create a profile</a></span> <label
				for="username">Email Id</label> <input id="username"
				name="username" type="text" autocapitalize="off" autocorrect="off">
			<label for="password">Password</label> <input id="password"
				name="password" type="password">
			<p class="usa-form-note">
				<a title="Show password" href="javascript:void(0);"
					class="usa-show_password" aria-controls="password"> Show
					password</a>
			</p>
			<input type="submit" value="Sign in" />
			<p>
				<a href="${pageContext.request.contextPath}/forgotpassword" title="Forgot password"> Forgot password?</a>
			</p>
		</fieldset>
	</form>
	
</div>
