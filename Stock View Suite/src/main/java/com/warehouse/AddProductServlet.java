import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    // Adds a new product to the database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        String message = "Product added successfully";

        // Validate input
        if (name == null || name.isEmpty() || quantityStr == null || priceStr == null) {
            message = "All fields are required.";
            redirectToDashboard(response, message);
            return;
        }

        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            message = "Invalid quantity or price format.";
            redirectToDashboard(response, message);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setInt(2, quantity);
                stmt.setDouble(3, price);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Error adding product: " + e.getMessage();
            e.printStackTrace();  // Consider using a logger in production
        }

        redirectToDashboard(response, message);
    }

    private void redirectToDashboard(HttpServletResponse response, String message) throws IOException {
        response.sendRedirect("employeeDashboard.jsp?message=" + message.replace(" ", "+"));
    }
}
