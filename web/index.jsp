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
            .search-form {
                margin-bottom: 20px;
            }
            .search-form input {
                width: 70%;
                padding: 10px;
                margin-right: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .search-form button {
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
            <h1>Search Products</h1>
            <form class="search-form" action="search" method="post">
                <input type="text" name="keyword" placeholder="Enter product name, description, price, or category" required>
                <button type="submit">Search</button>
            </form>
        </div>
    </body>
</html>
