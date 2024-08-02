<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <div class="container">
            <h1>Admin</h1>
            <a href="list_account">Danh sách tài khoản</a></br>
            <a href="list_category">Danh sách hạng mục</a></br>
            <a href="list_products">Danh sách sản phẩm</a></br>
            <a href="list_orders">Danh sách hóa đơn</a>
        </div>
        </br></br>
        <div>
            <button type="button" class="btn btn-danger" onclick="window.location.href = 'logout'">
                Đăng xuất
            </button>
        </div>
    </body>
</html>