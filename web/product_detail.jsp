<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin sản phẩm</title>
    </head>
    <body>
        <h1>Thông tin sản phẩm</h1>
        </br>
        <c:if test="${not empty product}">
            <p>ID: ${product.id}</p>
            <p>Tên sản phẩm: ${product.name}</p>
            <p>Hạng mục: ${p_category.name}</p>
            <p>Giá: ${product.price} VNĐ</p>
            <p>Hình ảnh: <img src="${product.image}" alt="${product.name}" height="200" width="150"></p>
            <p>Mô tả: ${product.description}</p>
        </c:if>

        <c:if test="${empty product}">
            <p>Sản phẩm không tồn tại.</p>
        </c:if>
        </br>
        <a href="list_products">Quay lại</a>
        </br></br>
        <div>
            <button type="button" class="btn btn-danger" onclick="window.location.href='logout'">
                Đăng xuất
            </button>
        </div>
    </body>
</html>
