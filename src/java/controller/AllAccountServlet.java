/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccInfo;
import model.User;
import service.AccInfoService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/list_account")
public class AllAccountServlet extends HttpServlet{
    private AccInfoService accInfoService;
    private UserService userService;
    
    @Override
    public void init(){
        this.accInfoService = new AccInfoService();
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
            List<AccInfo> accs = new ArrayList<>();
            try {
                accs = accInfoService.createListAccInfo();
                request.setAttribute("accs", accs);
                request.getRequestDispatcher("list_account.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if ("lock".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            userService.lockAcc(userId);
            response.sendRedirect("list_account?action=list");
        } else if ("unlock".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            userService.unLockAcc(userId);
            response.sendRedirect("list_account?action=list");
        }else {
            List<AccInfo> accs = new ArrayList<>();
            try {
                accs = accInfoService.createListAccInfo();
                request.setAttribute("accs", accs);
                request.getRequestDispatcher("list_account.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
