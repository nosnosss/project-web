<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật trạng thái</title>
    </head>
    <body>
        <h1>Cập nhật trạng thái</h1>
        <form action="update_order" method="post">
            <input type="hidden" name="orderId" value="${orderId}">
            
            <label for="status">Trạng thái:</label>
            <select id="status" name="status">
                <option value="pending" <c:if test="${orderStatus == 'pending'}">selected</c:if>>Pending</option>
                <option value="processed" <c:if test="${orderStatus == 'processed'}">selected</c:if>>Processed</option>
                <option value="canceled" <c:if test="${orderStatus == 'canceled'}">selected</c:if>>Canceled</option>
                <option value="completed" <c:if test="${orderStatus == 'completed'}">selected</c:if>>Completed</option>
            </select></br></br>

            <button type="submit">Lưu</button>
        </form>
        </br>
        <a href="list_orders">Quay lại</a>
    </body>
</html>
