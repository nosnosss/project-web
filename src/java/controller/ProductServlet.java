/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;
import model.User;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/list_products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;
    
    @Override
    public void init(){
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
        this.userService = new UserService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Object userIdObj = request.getSession().getAttribute("userId");
        
        if (userIdObj == null) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            int userId = (int) userIdObj;
            User u = userService.getUserById(userId);
            if(!u.getRole().equals("admin")){
                response.sendRedirect("memberHome.jsp");
                return;
            }
        }
        if ("list".equals(action)) {
            List<Product> products = new ArrayList<>();
            try {
                products = productService.listAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("list_products.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if("detail".equals(action)){
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product p = null;
            Category c = null;
            try {
                p = productService.getProductDetail(productId);
                c = categoryService.getCategoryDetail(p.getCategory_id());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            request.setAttribute("product", p);
            request.setAttribute("p_category", c);
            request.getRequestDispatcher("product_detail.jsp").forward(request, response);
        } else if("update".equals(action)){
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product p = null;
            Category c = null;
            List<Category> lc = new ArrayList<>();
            try {
                p = productService.getProductDetail(productId);
                c = categoryService.getCategoryDetail(p.getCategory_id());
                lc = categoryService.listAllCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            request.setAttribute("product", p);
            request.setAttribute("p_category", c);
            request.setAttribute("categories", lc);
            request.getRequestDispatcher("update_product.jsp").forward(request, response);
        } else if("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            productService.deleteProduct(productId);
            response.sendRedirect("list_products?action=list");
        } else {
            List<Product> products = new ArrayList<>();
            try {
                products = productService.listAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("list_products.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
