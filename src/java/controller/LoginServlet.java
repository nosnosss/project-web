package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle login logic here

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Example: Validate user credentials (you should add proper validation and error handling)
        // UserDao userDao = new UserDao();
        // User user = userDao.validateUser(username, password);
        // if (user != null) {
        //     request.getSession().setAttribute("user", user);
        //     response.sendRedirect("home");
        // } else {
        //     response.sendRedirect("login.jsp?error=true");
        // }

        // Temporary example: Redirect to home page after successful login
        response.sendRedirect("home");
    }
}
