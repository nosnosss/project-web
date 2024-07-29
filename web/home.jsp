<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Welcome, ${user.username}</h2>
    <a href="logout">Logout</a>
    <h3>Categories</h3>
    <ul>
        <c:forEach var="category" items="${categories}">
            <li>${category.name}</li>
        </c:forEach>
    </ul>
    <h3>Products</h3>
    <div>
        <c:forEach var="product" items="${products}">
            <div class="product">
                <h4>${product.name}</h4>
                <p>${product.description}</p>
                <p>Price: ${product.price}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
