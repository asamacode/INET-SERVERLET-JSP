<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Danh sách tổng hợp thông tin khách hàng</title>
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
					<li class="nav-item dropdown active"><a
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
        <h2>Tổng hợp thông tin khách hàng</h2>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Mã KH</th>
                    <th>Tên khách hàng</th>
                    <th>Mã máy</th>
                    <th>Vị trí</th>
                    <th>Trạng thái</th>
                    <th>Ngày SDM</th>
                    <th>Giờ SDM</th>
                    <th>Thời gian SD</th>
                    <th>Mã DV</th>
                    <th>Ngày SDDV</th>
                    <th>Giờ SDDV</th>
                    <th>Số lượng</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>KH001</td>
                    <td>Trần Hữu Lương</td>
                    <td>MAY001</td>
                    <td>100A</td>
                    <td>Đang hoạt động</td>
                    <td>08/10/2020</td>
                    <td>09:00</td>
                    <td>1</td>
                    <td>DV001</td>
                    <td>08/10/2020</td>
                    <td>10:00</td>
                    <td>8</td>
                    <td id="price">100000</td>
                </tr>
                <tr>
                    <td>KH001</td>
                    <td>Trần Hữu Lương</td>
                    <td>MAY001</td>
                    <td>100A</td>
                    <td>Đang hoạt động</td>
                    <td>08/10/2020</td>
                    <td>09:00</td>
                    <td>1</td>
                    <td>DV001</td>
                    <td>08/10/2020</td>
                    <td>10:00</td>
                    <td>8</td>
                    <td id="price">2000000</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td id="sum_price"></td>
                </tr>
            </tbody>
        </table>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</body>

<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>

<script>
    $(document).ready(function () {
        // Get all the elements into an array
        let cells = Array.prototype.slice.call(document.querySelectorAll("#price"));

        // Loop over the array
        var sum = 0;
        cells.forEach(function (cell) {
            // Convert cell data to a number, call .toLocaleString()
            // on that number and put result back into the cell
            sum += parseInt(cell.textContent);
            cell.textContent = (+cell.textContent).toLocaleString('en-US', { style: 'currency', currency: 'VND' });
        });

        document.getElementById('sum_price').innerHTML = sum.toLocaleString('en-US', { style: 'currency', currency: 'VND' });;
    });


</script>

</html>