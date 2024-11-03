<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard - Stock View Suite</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="dark-theme">
    <header>
        <h2>Warehouse Inventory Management</h2>
        <p>Welcome, <%= session.getAttribute("employee") != null ? session.getAttribute("employee") : "Employee" %></p>
        <a href="logout.jsp">Logout</a> <!-- Add a logout option -->
    </header>

    <main>
        <section>
            <h3>Add Product</h3>
            <form action="addProduct" method="post">
                <input type="text" name="name" placeholder="Product Name" required>
                <input type="number" name="quantity" placeholder="Quantity" required>
                <input type="number" step="0.01" name="price" placeholder="Price" required>
                <button type="submit">Add Product</button>
            </form>
        </section>

        <section>
            <h3>Update Product</h3>
            <form action="updateProduct" method="post">
                <input type="number" name="id" placeholder="Product ID" required>
                <input type="text" name="name" placeholder="Product Name">
                <input type="number" name="quantity" placeholder="Quantity">
                <input type="number" step="0.01" name="price" placeholder="Price">
                <button type="submit">Update Product</button>
            </form>
        </section>

        <section>
            <h3>Delete Product</h3>
            <form action="deleteProduct" method="post">
                <input type="number" name="id" placeholder="Product ID" required>
                <button type="submit">Delete Product</button>
            </form>
        </section>
    </main>
</body>
</html>
