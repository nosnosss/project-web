package controller;

import model.Category;
import model.Product;
import dao.CategoryDao;
import dao.ProductDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();
        try {
            List<Product> products = productDao.getAllProducts();
            List<Category> categories = categoryDao.getAllCategories();
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
