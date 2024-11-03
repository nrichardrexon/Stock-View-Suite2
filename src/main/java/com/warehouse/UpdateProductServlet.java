import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.warehouse.DatabaseConnection; // Adjust the path if needed

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {

    // Updates product information in the database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        // Validate input
        if (idStr == null || idStr.isEmpty() || name == null || name.isEmpty() || 
            quantityStr == null || quantityStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            response.sendRedirect("employeeDashboard.jsp?message=All+fields+are+required.");
            return;
        }

        int id;
        int quantity;
        double price;

        try {
            id = Integer.parseInt(idStr);
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("employeeDashboard.jsp?message=Invalid+input+format.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setInt(2, quantity);
                stmt.setDouble(3, price);
                stmt.setInt(4, id);
                
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    response.sendRedirect("employeeDashboard.jsp?message=Product+updated+successfully");
                } else {
                    response.sendRedirect("employeeDashboard.jsp?message=Product+not+found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("employeeDashboard.jsp?message=Error+updating+product.");
        }
    }
}
