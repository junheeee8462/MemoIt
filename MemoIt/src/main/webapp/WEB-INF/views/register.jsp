<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register.css">
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
