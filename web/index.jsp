<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Products</title>
    <style>
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
        }
        .search-box {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Products</h1>
        <form action="search" method="post">
            <div class="search-box">
                <input type="text" name="query" placeholder="Enter search query" required>
            </div>
            <div class="search-box">
                <label for="searchType">Search by:</label>
                <select id="searchType" name="searchType" required>
                    <option value="nameOrCategory">Name or Category</option>
                    <option value="descriptionOrPrice">Description or Price</option>
                </select>
            </div>
            <button type="submit">Search</button>
        </form>
    </div>
</body>
</html>
