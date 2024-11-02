<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Map<String, Object>> products = new ArrayList<>();

    try {
        conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM products";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Map<String, Object> product = new HashMap<>();
            product.put("id", rs.getInt("id"));
            product.put("name", rs.getString("name"));
            product.put("quantity", rs.getInt("quantity"));
            product.put("price", rs.getDouble("price"));
            products.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources to avoid memory leaks
        if (rs != null) try { rs.close(); } catch (SQLException e) {}
        if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
        if (conn != null) try { conn.close(); } catch (SQLException e) {}
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Products - Stock View Suite</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="dark-theme">
    <h1>Available Products</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Map<String, Object> product : products) {
            %>
            <tr>
                <td><%= product.get("id") %></td>
                <td><%= product.get("name") %></td>
                <td><%= product.get("quantity") %></td>
                <td><%= product.get("price") %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
