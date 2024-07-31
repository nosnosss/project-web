<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý tài khoản</title>
    </head>
    <body>
        <h1>Quản lý tài khoản</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Họ và tên</th>
                <th>Tên đăng nhập</th>
                <th>Email</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th>Lưa chọn</th>
            </tr>
            <c:forEach var="acc" items="${accs}">
                <tr>
                    <td>${acc.userId}</td>
                    <td>${acc.name}</td>
                    <td>${acc.username}</td>
                    <td>${acc.email}</td>
                    <td>${acc.role}</td>
                    <td>
                        <c:choose>
                            <c:when test="${acc.isActive}">Hoạt động</c:when>
                            <c:otherwise>Khóa</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${acc.isActive}">
                            <a href="list_account?action=lock&userId=${acc.userId}">Khóa</a>
                        </c:if>
                        <c:if test="${!acc.isActive}">
                            <a href="list_account?action=unlock&userId=${acc.userId}">Mở khóa</a>
                        </c:if>
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