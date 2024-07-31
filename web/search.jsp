<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
        <style>
            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }
            .product {
                border: 1px solid #ddd;
                margin: 10px 0;
                padding: 10px;
                border-radius: 5px;
                display: flex;
                align-items: center;
            }
            .product img {
                width: 100px;
                height: 100px;
                margin-right: 10px;
            }
            .product-details {
                flex: 1;
            }
            .buy-button {
                margin-left: 10px;
            }
            .buy-button button {
                padding: 5px 10px;
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
            <h1>Search Results</h1>
            <c:choose>
                <c:when test="${not empty products}">
                    <ul>
                        <c:forEach var="product" items="${products}">
                            <li class="product">
                                <img src="${product.image}" alt="${product.name}">
                                <div class="product-details">
                                    <strong>${product.name}</strong><br>
                                    Description: ${product.description}<br>
                                    Price: ${product.price}<br>
                                </div>
                                <form action="purchase" method="post" class="buy-button">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="hidden" name="productName" value="${product.name}">
                                    <input type="hidden" name="productPrice" value="${product.price}">
                                    <button type="submit">Mua hàng</button>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No products found</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
