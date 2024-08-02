<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết hạng mục</title>
    </head>
    <body>
        <h1>Chi tiết hạng mục</h1>
        </br>
        <c:if test="${not empty category}">
            <p>ID: ${category.id}</p>
            <p>Tên: ${category.name}</p>
        </c:if>

        <c:if test="${empty category}">
            <p>Hạng mục không tồn tại.</p>
        </c:if>
        </br>
        <a href="list_category">Quay lại</a>
        </br></br>
        <div>
            <button type="button" class="btn btn-danger" onclick="window.location.href='logout'">
                Đăng xuất
            </button>
        </div>
    </body>
</html>
