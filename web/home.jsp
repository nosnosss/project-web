<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center my-3">
        <h2>ABC Shop</h2>
        <div>
            <c:choose>
                <c:when test="${sessionScope.userId == null}">
                    <a href="login.jsp" class="btn btn-primary">Login</a>
                    <a href="register.jsp" class="btn btn-secondary">Register</a>
                </c:when>
                <c:otherwise>
                    <a href="logout" class="btn btn-danger">Logout</a>
                    <a href="orders.jsp" class="btn btn-info">View Orders</a>
                    <a href="ProfileServlet" class="btn btn-warning">Update Profile</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="mb-4">
        <h3>Categories</h3>
        <ul class="list-group">
            <c:forEach var="category" items="${categories}">
                <li class="list-group-item">${category.name}</li>
            </c:forEach>
        </ul>
    </div>

    <div>
        <h3>Products</h3>
        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <img src="${product.image}" class="card-img-top" alt="${product.name}">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">${product.description}</p>
                            <p class="card-text"><strong>Price:</strong> ${product.price}</p>
                            <p class="card-text"><strong>Category:</strong> ${product.categoryId}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
