<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>Profile</h1>
    <form method="post" action="ProfileServlet">
        <label>Name: </label>
        <input type="text" name="name" value="${profile.name}" required><br>
        <label>Address: </label>
        <input type="text" name="address" value="${profile.address}" required><br>
        <label>Phone Number: </label>
        <input type="text" name="phoneNumber" value="${profile.phoneNumber}" required><br>
        <button type="submit">Update Profile</button>
    </form>
</body>
</html>
