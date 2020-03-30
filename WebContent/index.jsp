<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Quản Lý Quán Net</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
<%@include file="css/bootstrap.min.css" %>
.btn {
	min-height: 150px;
	padding: 0px;
	justify-content: center;
}

.col {
	margin-top: 20px;
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
					<li class="nav-item active"><a class="nav-link"
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

		<div class="row">
			<div class="col">
				<a href="./machine/action?=add"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center"
					role="button"> Tạo Máy Mới</a>
			</div>
			<div class="col">
				<a href="./customer/action?=add" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Thêm
					Mới Khách Hàng</a>
			</div>
			<div class="col">
				<a href="./service/action?=add" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Tạo
					Mới Dịch Vụ</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<a href="./machine" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Liệt
					Kê Danh Sách Máy</a>
			</div>
			<div class="col">
				<a href="./customer" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Liệt
					Kê Danh Sách Khách Hàng</a>
			</div>
			<div class="col">
				<a href="./service" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Liệt
					Kê Danh Sách Dịch Vụ</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<a href="./machine?action=use" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Đăng
					Ký Sử Dụng Máy</a>
			</div>
			<div class="col">
				<a href="./service?action=use" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Đăng
					Ký Sử Dụng Dịch Vụ</a>
			</div>
			<div class="col">
				<a href="./customer/info" role="button"
					class="btn btn-outline-info btn-block btn-lg d-flex align-items-center">Liệt
					Kê Toàn Bộ Thông Tin Khách Hàng</a>
			</div>
		</div>
	</div>
</body>

<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>

</html>
