<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage User Profile by Admin</title>
</head>
<body>

	
<form:form commandName="user" action="${pageContext.request.contextPath}/approveOrReject" cssStyle="width: 800px;">


	<!------Success message----->


		<c:if test="${not empty param.message}">
			<div class="usa-alert usa-alert-success">
				<div class="usa-alert-body">
					<h3 class="usa-alert-heading">Success</h3>
					<p class="usa-alert-text">${param.message}</p>
				</div>
			</div>
		</c:if>
		
		 <!------Error message----->
	    <c:if test="${'notselected' eq param.error}">
	       <div class="usa-alert usa-alert-error" role="alert">
	    		<div class="usa-alert-body">
	      		<h3 class="usa-alert-heading">Error</h3>
	      		<p class="usa-alert-text">Please select a checkbox.</p>
	    	</div>
	    	</div>
	    </c:if>


		<table>
		<tr>
			<th></th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Department Name</th>
			<th>E-Mail ID</th>
		</tr>
		
			<c:forEach items="${user.userCheckBoxList}" var="r">
				<tr>
					<th><form:checkbox path="selectedUserBox" value="${r.userId}" label=""/></th>
					<th><c:out value="${r.firstName}"/></th>
					<th><c:out value="${r.lastName}"/></th>
					<th><c:out value="${r.department.departmentName}"></c:out></th>
					<th><c:out value="${r.emailId}"/></th>
				</tr>
			</c:forEach>
		</table>
	
	<br/>
	<tr>
	
	<td>
		<button class=" bg-primary" name="approveParam" value="approveVal">Approve</button>
	</td>
	<td>
		<button class=" bg-primary" name="approveParam" value="denyVal">Reject</button>
	</td>
	<td>
		<a class="checkout" href="loginform/signout"><i class="icon-minus-sign"></i> Logout </a>
	</td>
	</tr>
 
</form:form>
</body>
</html>