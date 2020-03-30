<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Chỉnh sửa thông tin máy</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
<%@include file="css/bootstrap.min.css" %>
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
			<a class="navbar-brand" href="./index.jsp">I-NET</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="./index.jsp">Trang
							Chủ <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Tạo Mới </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./new_machine.jsp">Tạo Máy Mới</a>
							<a class="dropdown-item" href="./new_customer.jsp">Thêm Mới
								Khách Hàng</a> <a class="dropdown-item" href="./new_service.jsp">Tạo
								Mới Dịch Vụ</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Liệt Kê </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./list_machine.jsp">Danh Sách
								Máy</a> <a class="dropdown-item" href="./list_customer.jsp">Danh
								Sách Khách Hàng</a> <a class="dropdown-item"
								href="./list_service.jsp">Danh Sách Dịch Vụ</a> <a
								class="dropdown-item" href="./info_customer.jsp">Toàn Bộ
								Thông Tin Khách Hàng</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Đăng Ký </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./use_machine.jsp">Sử Dụng Máy</a>
							<a class="dropdown-item" href="./use_service.jsp">Sử Dụng
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
			<div class="col-sm-4">
				<div class="card">
					<h3>Chỉnh sửa</h3>
					<br>
					<p>${NOTIFICATION}</p>
					<form
						action="${pageContext.request.contextPath}/machine?action=edit"
						method="post">
						<div class="form-group">
							<label for="code">Mã máy:</label> <input type="text"
								class="form-control" placeholder="Nhập vào mã máy" name="code"
								value="${computer.code}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="pos">Vị trí:</label> <input type="text"
								class="form-control" placeholder="Nhập vào vị trí máy" id="pos"
								name="postion" value="${computer.position}">
						</div>
						<div class="form-group">
							<label for="text">Trạng thái:</label> <select name="status"
								class="form-control">
								<c:choose>
									<c:when test="${computer.status}">
										<option selected="selected">Hoạt động</option>
										<option>Tắt</option>
									</c:when>
									<c:otherwise>
										<option>Hoạt động</option>
										<option selected="selected">Tắt</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Lưu lại</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>


</html>