<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/> 

<div id="maincontainer">
	<div class="container">
		<!--  breadcrumb -->
		<!-- <ul class="breadcrumb">
			<li><a href="productCatalogue">Home</a> <span class="divider">/</span></li>
			<li class="active">Order confirmation</li>
		</ul> -->
		<h1 class="heading1">
			<span class="maintext"> <i class="icon-tags"></i> Confirmation
			</span>
		</h1>

		<div id="content">
			<div class="manufacturer-list">
				<div class="manufacturer-heading">
					Current Order<a id="A"></a>
				</div>
				<div>
					<!-- widget content -->
					<div class="span8">
						<form class="form-horizontal">
							<fieldset>
								<div class="control-group">
									<label for="focusedInput" class="control-label">Department
										Name</label>
									<div class="controls">
										<input type="text" disabled="" value="${userDepartmentName}" id="focusedInput"
											class="input-xlarge focused uneditable-input">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Order Date</label>
									<div class="controls">
										<span class="input-xlarge uneditable-input"><fmt:formatDate  value="${now}" dateStyle="long" pattern="dd-MM-yyyy "/></span>
									</div>
								</div>
								<div class="control-group">
									<label for="disabledInput" class="control-label">Ship
										to</label>
									<div class="controls">
										<textarea type="text" disabled=""
											placeholder="Address come here"
											class="required input-xlarge disabled" rows="6" cols="40"
											id="message disabledInput" name="messagee">${shipToAddress}</textarea>

									</div>
								</div>
								<!-- <div class="control-group">
									<label for="focusedInput" class="control-label">Select
										with success</label>
									<div class="controls">
										<span class="help-inline">Item descriptions come
											here...</span>
									</div>
								</div> -->
								<div class="form-actions">
									<button class="btn btn-orange" type="button" onclick="location.href='saveOrder'">Submit
										order</button>
								</div>
							</fieldset>
						</form>
					</div>
					<!-- end widget content -->
				</div>
			</div>
			<div></div>

		</div>
	</div>