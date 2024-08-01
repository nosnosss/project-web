<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
</head>
<body>
    <h2>Order Details</h2>
    
    <c:if test="${not empty orderItems}">
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
            <c:forEach var="item" items="${orderItems}">
                <tr>
                    <td>${item.productId}</td>
                    <td>${item.productName}</td> <!-- Hiển thị tên sản phẩm -->
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.price}" type="currency" /></td>
                    <td><fmt:formatNumber value="${item.quantity * item.price}" type="currency" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty orderItems}">
        <p>No items found for this order.</p>
    </c:if>
    <a href="OrderServlet?action=viewOrders">Back to Orders</a>
    <a href="home">Back to Home</a>
</body>
</html>
