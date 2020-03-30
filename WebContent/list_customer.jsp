<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Danh sách khách hàng</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
<%@include file="css/bootstrap.min.css" %>
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
					<li class="nav-item"><a class="nav-link" href="./">Trang
							Chủ <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Tạo Mới </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine?action=add">Tạo Máy
								Mới</a> <a class="dropdown-item" href="./customer?action=add">Thêm
								Mới Khách Hàng</a> <a class="dropdown-item"
								href="./service?action=add">Tạo Mới Dịch Vụ</a>
						</div></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Liệt Kê </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine">Danh Sách Máy</a> <a
								class="dropdown-item" href="./customer">Danh Sách Khách Hàng</a>
							<a class="dropdown-item" href="./service">Danh Sách Dịch Vụ</a> <a
								class="dropdown-item" href="./customer?action=info">Toàn Bộ
								Thông Tin Khách Hàng</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Đăng Ký </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="./machine?action=use">Sử Dụng
								Máy</a> <a class="dropdown-item" href="./service?action=use">Sử
								Dụng Dịch Vụ</a>
						</div></li>
				</ul>
			</div>
		</nav>
	</div>
</header>
<!-- Header -->

<body>
	<div class="container">
		<h2>Danh sách khách hàng</h2>
		<div class="row" style="margin-top: 5px; margin-bottom: 10px;">
			<div class="col-sm-4">
				<p>Tìm kiếm thông tin khách hàng:</p>
			</div>
			<div class="col-sm-4">
				<form class="form-inline">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Nhập tên khách hàng" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm
						kiếm</button>
				</form>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Mã KH</th>
					<th>Tên khách hàng</th>
					<th>Địa chỉ</th>
					<th>Số điện thoại</th>
					<th>Email</th>
					<th>Hành động</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listCustomer}" var="customer">
					<tr data-id="${customer.id}" >
						<td>${customer.id}</td>
						<td>${customer.name}</td>
						<td>${customer.address}</td>
						<td>${customer.phone}</td>
						<td>${customer.email}</td>
						<td><a
							href="${pageContext.request.contextPath}/customer?action=edit&customer_id=${customer.id}"
							class="btn btn-outline-primary">Chỉnh sửa</a></td>
						<td><button class="btn btn-outline-danger"
								onclick="showModal(this);">Xóa</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						<span class="sr-only">Previous</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
						class="sr-only">Next</span>
				</a></li>
			</ul>
		</nav>
		<!-- Modal -->
		<div class="modal" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">I-NET</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Bạn có chắc chắn muốn xóa ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger"
							onclick="deleteRow();">Xóa</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Đóng</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
	</div>
</body>
<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>
<script>
	function showModal(inputField) {
		$('.modal').modal('show');
		$('tr.selected').removeClass('selected');
		// add selected class to current row
		$(inputField).closest('tr').addClass('selected');
	}

	function deleteRow() {
		var id = $('tr.selected').data("id");
		$('tr.selected').remove();
		$('.modal').modal('toggle');
		
		 $.ajax({
	            type: "post",
	            url: "customer", 
	            data: {
	            	action: "delete",
	            	customer_id: id
	            },
	            success: function(msg){     
	            	
	            },
	            error: function() {
	    
	            }
	        });
	}
</script>


</html>