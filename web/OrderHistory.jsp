<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
</head>
<body>
    <h1>Order History</h1>
    <form method="get" action="OrderServlet">
        <input type="hidden" name="action" value="filterOrders">
        From: <input type="date" name="fromDate" required>
        To: <input type="date" name="toDate" required>
        <button type="submit">Filter</button>
    </form>

    <c:if test="${not empty orders}">
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Status</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                    <th>Payment Method</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.status}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.paymentMethod}</td>
                        <td>${order.time}</td>
                        <td>
                            <c:if test="${order.status eq 'pending'}">
                                <form action="OrderServlet" method="get">
                                    <input type="hidden" name="action" value="cancelOrder">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <button type="submit">Cancel</button>
                                </form>
                            </c:if>
                            <c:if test="${order.status eq 'canceled'}">
                                <form action="OrderServlet" method="get">
                                    <input type="hidden" name="action" value="reorder">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <button type="submit">Reorder</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
