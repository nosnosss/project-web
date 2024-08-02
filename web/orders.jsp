<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
</head>
<body>
    <h2>Order History</h2>
    
    <% Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) { %>
        <p>Please <a href="login.jsp">log in</a> to view your orders.</p>
    <% } else { %>
        <form action="OrderServlet" method="get">
            <input type="hidden" name="action" value="filterOrders">
            From: <input type="date" name="fromDate" required>
            To: <input type="date" name="toDate" required>
            <input type="submit" value="Filter">
        </form>
        
        <h3>All Orders</h3>
        <c:if test="${not empty orders}">
            <table border="1">
                <tr>
                    <th>Order ID</th>
                    <th>Status</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                    <th>Payment Method</th>
                    <th>Time</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.status}</td>
                        <td><fmt:formatNumber value="${order.totalAmount}" type="currency" /></td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.paymentMethod}</td>
                        <td><fmt:formatDate value="${order.time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <a href="OrderServlet?action=viewOrderDetails&orderId=${order.id}">View Details</a>
                            <c:if test="${order.status == 'pending'}">
                                <a href="OrderServlet?action=cancelOrder&orderId=${order.id}">Cancel</a>
                            </c:if>
                            <c:if test="${order.status == 'canceled'}">
                                <a href="OrderServlet?action=reorder&orderId=${order.id}">Reorder</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty orders}">
            <p>No orders found.</p>
        </c:if>
    <% } %>
    <a href="home">Back to Home</a>
</body>
</html>
