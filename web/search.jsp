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
            text-align: center;
        }
        .product {
            border: 1px solid #ddd;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
        }
        .product h3 {
            margin: 0;
            font-size: 24px;
        }
        .product p {
            margin: 5px 0;
        }
        .product form {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Results</h1>
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <div class="product">
                    <h3>${product.name}</h3>
                    <p>Description: ${product.description}</p>
                    <p>Price: ${product.price}</p>
                    <form action="purchase.jsp" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="productName" value="${product.name}">
                        <input type="hidden" name="productPrice" value="${product.price}">
                        <button type="submit">Mua hàng</button>
                    </form>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty products}">
            <p>Không tìm thấy sản phẩm</p>
        </c:if>
    </div>
</body>
</html>
