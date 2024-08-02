//package controller;
//
//import dao.UserDao;
//import model.User;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.regex.Pattern;
//
//@WebServlet("/register")
//public class RegisterServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private UserDao userDao;
//
//    public void init() {
//        userDao = new UserDao();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        String errorMessage = validateInput(username, password, email);
//        if (errorMessage != null) {
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//            return;
//        }
//
//        if (userDao.isUsernameTaken(username)) {
//            request.setAttribute("errorMessage", "Username already in use.");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//        } else if (userDao.isEmailTaken(email)) {
//            request.setAttribute("errorMessage", "Email already in use.");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//        } else {
//            User user = new User(username, password, email, "member", false);
//            boolean isRegistered = userDao.registerUser(user);
//
//            if (isRegistered) {
//                response.sendRedirect("login.jsp");
//            } else {
//                request.setAttribute("errorMessage", "Registration failed. Please try again.");
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//            }
//        }
//    }
//
//    private String validateInput(String username, String password, String email) {
//        if (!Pattern.matches("^[a-zA-Z0-9]{3,15}$", username)) {
//            return "Username must be alphanumeric and 3-15 characters long.";
//        }
//        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", password)) {
//            return "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one digit, and one special character.";
//        }
//        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
//            return "Invalid email format.";
//        }
//        return null;
//    }
//}
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
