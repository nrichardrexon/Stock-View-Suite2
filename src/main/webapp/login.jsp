<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Login - Stock View Suite</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="dark-theme">
    <div class="container">
        <h2>Employee Login</h2>
        <form action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Login</button>
        </form>
        
        <% if (request.getParameter("error") != null) { %>
            <p class="error" style="color: red;"><%= request.getParameter("error") %></p>
        <% } %>

        <p>Don't have an account? <a href="register.jsp">Register here</a></p> <!-- Optional: Registration link -->
    </div>
</body>
</html>
