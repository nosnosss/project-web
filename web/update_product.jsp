<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật sản phẩm</title>
    </head>
    <body>
        <h1>Cập nhật sản phẩm</h1></br></br>
        <form action="update_product" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="${product.id}">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" id="name" name="name" value="${product.name}" required></br></br>

            <label for="categoryId">Hạng mục:</label>
            <select id="categoryId" name="categoryId">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}"
                            <c:if test="${p_category.id == category.id}">selected</c:if>>
                        ${category.name}
                    </option>
                </c:forEach>
            </select></br></br>

            <label for="description">Mô tả:</label>
            <textarea id="description" name="description" required>${product.description}</textarea></br></br>

            <label for="price">Giá:</label>
            <input type="number" id="price" name="price" value="${product.price}" required></br></br>

            <label for="image">Ảnh:</label>
            <input type="file" id="image" name="image" accept="image/*"></br></br>

            <c:if test="${not empty product.image}">
                <img src="${product.image}" width="100">
            </c:if>
             
            </br></br>

            <button type="submit">Lưu</button>
        </form>
        </br>
        <a href="list_products">Quay lại</a>
        </br></br>
        <div>
            <button type="button" class="btn btn-danger" onclick="window.location.href='logout'">
                Đăng xuất
            </button>
        </div>
    </body>
</html>
