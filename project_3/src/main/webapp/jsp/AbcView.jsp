<%@page import="in.co.rays.project_3.controller.AbcCtl"%>
<%@page import="java.util.Map"%>
<%@page import="in.co.rays.project_3.controller.TransportationCtl"%>
<%@page import="java.util.List"%>

<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/validateInput.js"></script>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABC view</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/utilities.js"></script>

<script type="text/javascript">
	function validateMobileNo(event) {
		const input = event.target;
		input.value = input.value.replace(/[^0-9.]/g, '')
		if (input.value.length > 0 && input.value[0] <= '5') {
			input.value = '';
		}
	}
</script>

<script type="text/javascript">
	function numberLength(input) {
		if (input.value.length > 10) {
			input.value = input.value.slice(0, 10);
		}
	}
</script>

<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2024',
		//  mindefaultDate : "01-01-1962"
		});
	});
</script>
<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
	background-image: linear-gradient(to bottom right, purple, white);
	background-repeat: no repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/download (3).jpeg');
	background-size: cover;
	padding-top: 6%;
}
</style>

</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.ABC_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.AbcDTO"
				scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (dto.getId() != null && id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update ABC
							</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add ABC</h3>
							<%
								}
							%>
							<!--Body-->
							<div>

								<%
									Map map = (Map) request.getAttribute("modee");
								%>



								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getId()%>">

							</div>

							<div class="md-form">


								<span class="pl-sm-5"><b> Name</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-alt grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" name="name"
											placeholder="Enter Name" class="form-control"
											oninput="handleLetterInput(this, 'nameError', 20)"
											onblur="validateLetterInput(this, 'nameError', 20)"
												value="<%=DataUtility.getStringData(dto.getName())%>">


									</div>
								</div>

								<font color="red" class="pl-sm-5" id="nameError"> <%=ServletUtility.getErrorMessage("name", request)%></font></br>


								<span class="pl-sm-5"><b>Date</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" id="datepicker" name="date"
											class="form-control" placeholder="Enter date"
											value="<%=DataUtility.getDateString(dto.getDate())%>">


									</div>

								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("date", request)%></font></br>





								<span class="pl-sm-5"><b>Type</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-circle grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>


										<%=HTMLUtility.getList1("type", String.valueOf(dto.getType()), map)%>

									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("type", request)%></font></br>


								<span class="pl-sm-5"><b>Cost</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="cost"
											placeholder=" Enter cost"
											oninput="handleIntegerInput(this, 'costError', 8)"
											onblur="validateIntegerInput(this, 'costError', 8)"
											value="<%=DataUtility.getStringData(dto.getCost()).equals("0") ? ""
					: DataUtility.getStringData(dto.getCost())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5" id="costError"> <%=ServletUtility.getErrorMessage("cost", request)%></font></br>


								<span class="pl-sm-5"><b>Phone Number</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="phoneNumber"
											placeholder=" Enter Phone Number"
											oninput="handleIntegerInput(this, 'phoneNumberError', 10)"
											onblur="validateIntegerInput(this, 'phoneNumberError', 10)"
											value="<%=DataUtility.getStringData(dto.getPhoneNumber()).equals("0") ? ""
					: DataUtility.getStringData(dto.getPhoneNumber())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5" id="phoneNumberError">
									<%=ServletUtility.getErrorMessage("phoneNumber", request)%></font></br> <span
									class="pl-sm-5"><b>Description</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<textarea name="description" placeholder="Enter description"
											class="form-control"
											oninput="handleLetterInput(this, 'descriptionError', 100)"
											onblur="validateLetterInput(this, 'descriptionError', 100)"
											onkeypress="return validateInput(event)" rows="5" cols="5"><%=DataUtility.getStringData(dto.getDescription())%></textarea>

									</div>
								</div>

								<font color="red" class="pl-sm-5" id="descriptionError">
									<%=ServletUtility.getErrorMessage("description", request)%></font></br>





								<%
									if (dto.getId() != null && id > 0) {
								%>

								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=AbcCtl.OP_UPDATE%>"> <input
										type="submit" name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px"
										value="<%=AbcCtl.OP_CANCEL%>">

								</div>
								<%
									} else {
								%>
								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=AbcCtl.OP_SAVE%>"> <input
										type="submit" name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px"
										value="<%=AbcCtl.OP_RESET%>">
								</div>

							</div>
							<%
								}
							%>
						</div>
					</div>
		</form>
		</main>
		<div class="col-md-4 mb-4"></div>

	</div>

</body>
<%@include file="FooterView.jsp"%>

</body>
</html>


