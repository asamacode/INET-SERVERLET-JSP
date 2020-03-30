<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="urf-8">
<title>Chỉnh sửa thông tin khách hàng</title>
<style>
<%@ include file ="css/bootstrap.min.css" %> .card {
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
					<li class="nav-item dropdown active"><a
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
					<li class="nav-item dropdown"><a
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
					<h3>Thông tin khách hàng</h3>
					<br>
					<p>${NOTIFICATION}</p>
					<form
						action="${pageContext.request.contextPath}/customer?action=edit"
						method="POST">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="text">Mã khách hàng:</label> <input type="text" value="${customer.id}"
										class="form-control" placeholder="Nhập vào mã khách hàng"
										name="customer_id" onchange="validateMaKH(this);" readonly="readonly">
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="text">Tên khách hàng:</label> <input type="text" value="${customer.name}"
										class="form-control" placeholder="Nhập vào tên khách hàng"
										name="customer_name" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="text">Địa chỉ:</label> <input type="text" value="${customer.address}"
								class="form-control" placeholder="Nhập vào địa chỉ"
								name="customer_address">
						</div>
						<div class="form-group">
							<label for="mobile">Số điện thoại:</label> <input type="number" value="${customer.phone}"
								class="form-control" placeholder="Nhập vào số điện thoại"
								name="customer_phone" onchange="validatePhone(this);">
						</div>
						<div class="form-group">
							<label for="email">Địa chỉ email:</label> <input type="email" value="${customer.email}"
								class="form-control" placeholder="Nhập vào địa chỉ email"
								name="customer_email" onchange="validateEmail(this);">
						</div>
						<button type="submit" class="btn btn-primary" id="btn_create">Lưu lại</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>

</html>