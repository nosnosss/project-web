<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật hạng mục</title>
    </head>
    <body>
        <h1>Cập nhật hạng mục</h1>
        <form method="POST" action="update_category">
            <input type="hidden" name="id" value="${category.id}">
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="${category.name}" required></br></br>

            <button type="submit">Cập nhật</button>
        </form>
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
