<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <script>
        function checkChanges() {
            var initialName = document.getElementById("name").dataset.initial;
            var initialAddress = document.getElementById("address").dataset.initial;
            var initialPhoneNumber = document.getElementById("phoneNumber").dataset.initial;
            
            var currentName = document.getElementById("name").value;
            var currentAddress = document.getElementById("address").value;
            var currentPhoneNumber = document.getElementById("phoneNumber").value;

            var saveButton = document.getElementById("saveButton");

            if (currentName !== initialName || currentAddress !== initialAddress || currentPhoneNumber !== initialPhoneNumber) {
                saveButton.disabled = false;
            } else {
                saveButton.disabled = true;
            }
        }
    </script>
</head>
<body>
    <h1>Profile</h1>

    <c:if test="${not empty updateSuccess}">
        <p style="color:green;">Profile updated successfully!</p>
    </c:if>

    <c:if test="${not empty updateSuccess && !updateSuccess}">
        <p style="color:red;">Profile update failed. Please try again.</p>
    </c:if>

    <form method="post" action="ProfileServlet">
        <label>Name: </label>
        <input type="text" id="name" name="name" value="${profile.name}" data-initial="${profile.name}" required oninput="checkChanges()"><br>
        <label>Address: </label>
        <input type="text" id="address" name="address" value="${profile.address}" data-initial="${profile.address}" required oninput="checkChanges()"><br>
        <label>Phone Number: </label>
        <input type="text" id="phoneNumber" name="phoneNumber" value="${profile.phoneNumber}" data-initial="${profile.phoneNumber}" required oninput="checkChanges()"><br>
        <button type="submit" id="saveButton" disabled>Update Profile</button>
    </form>
    <a href="home.jsp">Back to Home</a>
</body>
</html>
