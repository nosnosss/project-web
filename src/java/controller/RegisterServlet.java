package controller;

import dao.UserDao;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (userDao.isUsernameTaken(username)) {
            request.setAttribute("errorMessage", "Username already in use.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (userDao.isEmailTaken(email)) {
            request.setAttribute("errorMessage", "Email already in use.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            User user = new User(username, password, email, "member", false);
            boolean isRegistered = userDao.registerUser(user);

            if (isRegistered) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }
}
