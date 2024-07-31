<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sản phẩm</title>
    </head>
    <body>
        <h1>Danh sách sản phẩm</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Giá</th>
                <th>Hình ảnh</th>
                <th>Lựa chọn</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td><img src="${product.image}" alt="${product.name}" height="50"></td>
                    <td>
                        <a href="list_products?action=detail&productId=${product.id}">Xem</a>
                        <a href="list_products?action=update&productId=${product.id}">Sửa</a>
                        <a href="list_products?action=delete&productId=${product.id}">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </br>
        <a href="adminHome">Quay lại</a>
        </br></br>
        <div>
            <button type="button" class="btn btn-danger" onclick="window.location.href = 'logout'">
                Đăng xuất
            </button>
        </div>
    </body>
</html>
