<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Đăng ký sử dụng máy</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<style>
<%@ include file ="css/bootstrap.min.css" %> 
.card {
	margin-top: 50px;
	padding: 50px;
}
</style>
</head>
<!-- Header -->
<header>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light"
			style="background-color: #e3f2fd;">
			<a class="navbar-brand" href="./">I-NET</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="./">Trang Chủ <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Tạo Mới </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine?action=add">Tạo Máy Mới</a>
							<a class="dropdown-item" href="./customer?action=add">Thêm Mới
								Khách Hàng</a> <a class="dropdown-item" href="./service?action=add">Tạo
								Mới Dịch Vụ</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Liệt Kê </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine">Danh Sách
								Máy</a> <a class="dropdown-item" href="./customer">Danh
								Sách Khách Hàng</a> <a class="dropdown-item"
								href="./service">Danh Sách Dịch Vụ</a> <a
								class="dropdown-item" href="./customer?action=info">Toàn Bộ
								Thông Tin Khách Hàng</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Đăng Ký </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine?action=use">Sử Dụng Máy</a>
							<a class="dropdown-item" href="./service?action=use">Sử Dụng
								Dịch Vụ</a>
						</div></li>
				</ul>
			</div>
		</nav>
	</div>
</header>
<!-- Header -->

<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-sm-8">
				<div class="card">
					<h3>Đăng ký sử dụng máy:</h3>
					<br>
					<form action="${pageContext.request.contextPath}/machine?action=use" method="post">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="text">Mã máy:</label> <select class="form-control" name="machineCode">
									<c:forEach items="${listMachine}" var="machine">
										<option>${machine}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="text">Mã khách hàng:</label> <select name="customerId"
										class="form-control">
										<c:forEach items="${listCustomer}" var="customer">
										<option>${customer}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="text">Ngày sử dụng:</label> <input id="datepicker" name="start_date"
								width="270" placeholder="Chọn ngày" onchange="validateDate(this);"/>
						</div>
						<div class="form-group">
							<label for="money">Giờ sử dụng:</label> <input id="timepicker" name="start_time"
								width="276" placeholder="Chọn giờ" onchange="validateHhMm(this);" />
						</div>
						<div class="form-group">
							<label for="time">Thời gian:</label> <input type="number" name="use_time"
								placeholder="Nhập thời gian" class="form-control col-md-5"
								id="time" onchange="validateUseTime(this);" />
						</div>
						<button type="submit" class="btn btn-primary">Đăng ký</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>

</html>