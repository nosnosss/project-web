<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn hàng</title>
    </head>
    <body>
        <form action="list_orders" method="get">
            <input type="hidden" name="action" value="filterOrders">
            Từ ngày: <input type="date" name="fromDate" value = "${select_f_date}" required>
            Đến ngày: <input type="date" name="toDate" value = "${select_t_date}" required>
            Người dùng:
            <select name="userId">
                <option value="0">All</option>
                <c:forEach var="acc" items="${accs}">
                    <option value="${acc.userId}"  
                            <c:if test="${select_id == acc.userId}">selected</c:if>>
                        ${acc.name} - ${acc.username}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" value="Lọc">
        </form>

        <h1>Danh sách đơn hàng</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Trạng thái</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái thanh toán</th>
                    <th>Phương thức thanh toán</th>
                    <th>Thời gian</th>
                    <th>Cập nhật trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.userId}</td>
                        <td>${order.status}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.paymentMethod}</td>
                        <td>${order.time}</td>
                        <th>
                            <button type="button" class="btn btn-danger" onclick="window.location.href = 'list_orders?action=update&orderId=${order.id}'">
                                Cập nhật
                            </button>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
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
