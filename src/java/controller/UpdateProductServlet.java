/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/update_product")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 10, // 10 MB
    maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class UpdateProductServlet extends HttpServlet {

    private ProductService productService;
    private UserService userService;

    @Override
    public void init() {
        this.productService = new ProductService();
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        String image = "";
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            String imagePath = "image_upload/" + fileName;
            filePart.write(getServletContext().getRealPath(imagePath));
            image = imagePath;
        }
        try {
            productService.updateProduct(productId, name, description, price, categoryId, image);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("list_products?action=list");
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] parts = contentDisposition.split(";");
        for (String part1 : parts) {
            if (part1.trim().startsWith("filename")) {
                return part1.substring(part1.indexOf("=") + 1).trim().replaceAll("\"", "");
            }
        }
        return "";
    }
}
