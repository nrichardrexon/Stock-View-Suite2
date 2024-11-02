import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    // Deletes a product from the database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String message = "Product deleted successfully";

        // Validate input
        if (idStr == null || idStr.isEmpty()) {
            message = "Product ID is required.";
            redirectToDashboard(response, message);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            message = "Invalid product ID format.";
            redirectToDashboard(response, message);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM products WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                
                // Check if the product was actually deleted
                if (rowsAffected == 0) {
                    message = "No product found with the given ID.";
                }
            }
        } catch (SQLException e) {
            message = "Error deleting product: " + e.getMessage();
            e.printStackTrace();  // Consider using a logger in production
        }

        redirectToDashboard(response, message);
    }

    private void redirectToDashboard(HttpServletResponse response, String message) throws IOException {
        response.sendRedirect("employeeDashboard.jsp?message=" + message.replace(" ", "+"));
    }
}
