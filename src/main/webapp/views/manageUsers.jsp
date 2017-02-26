<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js" type="text/javascript"></script>

<script type="text/javascript">
	
	
$(document).ready(function() {
	
	
	//Next clicks
	$(".cnext").click(function() {
		
		 var checkboxValues = [];
	        $('input:checkbox:not(:checked)').each(function(index, elem) {
	            checkboxValues.push($(elem).val());
	        });

		var data = $("#mForm").serialize();
		
	    window.location = this.href + "?"+data+"&"+"unchecked="+checkboxValues;
	    return(false);
	});
	
	
	//Prev clicks
	$(".cprev").click(function() {
		
		 var checkboxValues = [];
	        $('input:checkbox:not(:checked)').each(function(index, elem) {
	            checkboxValues.push($(elem).val());
	        });

		var data = $("#mForm").serialize();
		
	    window.location = this.href + "?"+data+"&"+"unchecked="+checkboxValues;
	    return(false);
	});
	
	
	
	//Page number hyperlink.
	$(".anyp").click(function() {
		
		 var checkboxValues = [];
	     $('input:checkbox:not(:checked)').each(function(index, elem) {
	         checkboxValues.push($(elem).val());
	     });
	
		var data = $("#mForm").serialize();
		
	 	window.location = this.href + "?"+data+"&"+"unchecked="+checkboxValues;
	 	return(false);
	});
	
});




</script>
	
	
<div class="container">
	<div class="box">
		<div class="row">
			<div class="col-sm-6">
				<h4>Manage User Registration Request</h4>
			</div>
			<div class="col-sm-6 text-right"></div>
		</div>
		
		<form name="form1" id="mForm" action="${pageContext.request.contextPath}/approveOrReject">
		
		
    	<!------Success message----->


		<c:if test="${not empty param.message}">
			<div class="usa-alert usa-alert-success">
				<div class="usa-alert-body">
					<h3 class="usa-alert-heading">${param.approvedOrRejected}</h3>
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
	    
	    
		<div class="table-responsive">
			<c:set var="pageListHolder" value="${userList}" scope="session" />
			
			<spring:url value="/renderPaginationViewAll" var="pageurl" />
			<table class="table std-table table-striped">
				<thead>
					<tr>
						<th></th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Department Name</th>
						<th>E-mail ID</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ph" items="${pageListHolder.pageList}">
						<tr>
							<td><input type="checkbox" id="check_id" class="chk" value="${ph.userId}" name="selectedUserBox" ${ph.selectPreference}/></td>
							<td>${ph.firstName}</td>
							<td>${ph.lastName}</td>
							<td>${ph.department.departmentName}</td>
							<td>${ph.emailId}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-sm-6 xs-text-center sm-text-left">
				<button class="btn btn-primary" name="approveParam" value="approveVal">
					<i class="fa fa-check"></i> Approve
				</button>
				<button class="btn btn-danger" name="approveParam" value="denyVal">
					<i class="fa fa-close"></i> Reject
				</button>
			</div>
			
				<!-- pagination -->
			<div class="col-sm-6 xs-text-center sm-text-right">
					<ul class="pagination">
						
						<!-- Previous page -->
						<c:choose>
							<c:when test="${pageListHolder.firstPage}">
								<li>
								  <a href="#" aria-label="Previous">
									<span aria-hidden="true">Previous</span>
								  </a>
								</li>
							</c:when>
							<c:otherwise>	
								<li>
									<span aria-hidden="true"><a class="cprev" href="${pageurl}/prev">Previous</a></span>
								</li>
							</c:otherwise>
						</c:choose>
						
						
						<!-- Any page -->
						<c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">
							<c:choose>
								<c:when test="${loop.index == pageListHolder.page}">
									<li class="active"><a href="#">${loop.index+1}</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="anyp" href="${pageurl}/${loop.index}">${loop.index+1}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>


						<!-- Next page -->
						<c:choose>
							<c:when test="${pageListHolder.lastPage}">
								<li>
								  <a href="#" aria-label="Next">
									<span aria-hidden="true">Next</span>
								  </a>
								</li>
							</c:when>
							<c:otherwise>	
								<li>
									<span aria-hidden="true"><a class="cnext" href="${pageurl}/next">Next</a></span>
								</li>
							</c:otherwise>
						</c:choose>
						
						
					  </ul>
				</div>
			<!-- pagination -->
			
		</div>
		</form>
	</div>
</div>
