package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Profile;
import service.ProfileService;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private ProfileService profileService;

    @Override
    public void init() {
        profileService = new ProfileService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Profile profile = profileService.getProfileByUserId(userId);
        if (profile != null) {
            request.setAttribute("profile", profile);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setName(name);
        profile.setAddress(address);
        profile.setPhoneNumber(phoneNumber);

        boolean updateSuccess = profileService.updateProfile(profile);
        if (updateSuccess) {
            // Cập nhật thành công, chuyển hướng đến trang chính (home)
            response.sendRedirect("home.jsp");
        } else {
            // Cập nhật thất bại, hiển thị thông báo lỗi trên cùng trang
            request.setAttribute("updateSuccess", false);
            request.setAttribute("profile", profile);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
