/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author boyca
 */
@WebServlet("/login")
public class LoginAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();
        User user = userDao.validateUser(username, password);

        if (user != null && user.getRole().equals("admin") && user.isLocked()==false) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId()); // Lưu userId vào session
            response.sendRedirect("adminHome");
        } else if(user != null && !user.getRole().equals("admin") && user.isLocked()==false){
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId()); // Lưu userId vào session
            response.sendRedirect("memberHome.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
