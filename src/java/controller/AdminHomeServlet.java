package controller;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;

@WebServlet("/adminHome")
public class AdminHomeServlet extends HttpServlet {
    private UserService userService;
    
    @Override
    public void init(){
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        request.getRequestDispatcher("adminHome.jsp").forward(request, response);
    }

}
