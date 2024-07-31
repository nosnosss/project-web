/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.User;
import service.CategoryService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/list_category")
public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService;
    private UserService userService;
    
    @Override
    public void init(){
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
            List<Category> cates = new ArrayList<>();
            try {
                cates = categoryService.listAllCategory();
                request.setAttribute("cates", cates);
                request.getRequestDispatcher("list_categories.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if("detail".equals(action)){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category c = categoryService.getCategoryDetail(categoryId);
            request.setAttribute("category", c);
            request.getRequestDispatcher("category_detail.jsp").forward(request, response);
        } else if("update".equals(action)){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category c = categoryService.getCategoryDetail(categoryId);
            request.setAttribute("category", c);
            request.getRequestDispatcher("update_category.jsp").forward(request, response);
        } else if("delete".equals(action)) {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            try {
                categoryService.deleteCategory(categoryId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("list_category");
        }else {
            List<Category> cates = new ArrayList<>();
            try {
                cates = categoryService.listAllCategory();
                request.setAttribute("cates", cates);
                request.getRequestDispatcher("list_categories.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
