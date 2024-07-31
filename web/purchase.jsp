<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Purchase</title>
        <style>
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
                text-align: center;
            }
            .purchase-form {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .purchase-form label {
                margin-top: 10px;
            }
            .purchase-form input[type="text"],
            .purchase-form input[type="number"],
            .purchase-form select {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .purchase-form button {
                margin-top: 20px;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: #28a745;
                color: white;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Purchase</h1>
            <form class="purchase-form" action="finalizePurchase" method="post">
                <input type="hidden" name="productId" value="${order.productId}">
                <input type="hidden" name="productName" value="${order.productName}">
                <input type="hidden" name="productPrice" value="${order.productPrice}">

                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="quantity" value="1" min="1" required>

                <label for="paymentMethod">Payment Method</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="cash">Cash</option>
                    <option value="credit card">Credit Card</option>
                    <option value="paypal">PayPal</option>
                </select>

                <label>Product Information</label>
                <p>Product Name: ${order.productName}</p>
                <p>Price: ${order.productPrice}</p>

                <label>User Information</label>
                <p>Username: ${sessionScope.username}</p>
                <p>Email: ${sessionScope.email}</p>

                <button type="submit">Mua</button>
            </form>
        </div>
    </body>
</html>
