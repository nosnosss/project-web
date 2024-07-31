/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.User;
import service.CategoryService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/update_category")
public class UpdateCategoryServlet extends HttpServlet {
    private CategoryService categoryService;
    private UserService userService;
    
    @Override
    public void init(){
        this.categoryService = new CategoryService();
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
        int categoryId = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("name");
        categoryService.updateCategory(categoryId, categoryName);
        response.sendRedirect("list_category");
    }

}
