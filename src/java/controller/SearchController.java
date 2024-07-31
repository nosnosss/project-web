package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        String searchType = request.getParameter("searchType");

        List<Product> products;
        if ("nameOrCategory".equals(searchType)) {
            products = productDAO.searchProductsByNameOrCategory(query);
        } else {
            products = productDAO.searchProductsByDescriptionOrPrice(query);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
