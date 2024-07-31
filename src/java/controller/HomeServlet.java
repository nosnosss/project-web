package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    public void init() {
        categoryDao = new CategoryDao();
        productDao = new ProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = null;
        try {
            categories = categoryDao.getAllCategories();
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Product> products = null;
        try {
            products = productDao.getAllProducts();
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
