<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"><br>
        <input type="submit" value="Register">
    </form>
    <a href="login.jsp">Login</a>
</body>
</html>
