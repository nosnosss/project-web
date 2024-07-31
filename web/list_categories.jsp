<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý hạng mục</title>
    </head>
    <body>
        <h1>Quản lý hạng mục</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Tên</th>
            </tr>
            <c:forEach var="cate" items="${cates}">
                <tr>
                    <td>${cate.id}</td>
                    <td>${cate.name}</td>
                    <td>
                        <a href="list_category?action=detail&categoryId=${cate.id}">Xem</a>
                        <a href="list_category?action=update&categoryId=${cate.id}">Sửa</a>
                        <a href="list_category?action=delete&categoryId=${cate.id}">Xóa</a>
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