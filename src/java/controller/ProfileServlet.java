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
            // Nếu userId không tồn tại, chuyển hướng đến trang đăng nhập hoặc thông báo lỗi
            response.sendRedirect("login.jsp");
            return;
        }

        Profile profile = profileService.getProfileByUserId(userId);
        if (profile != null) {
            request.setAttribute("profile", profile);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            // Nếu không tìm thấy hồ sơ người dùng, có thể chuyển hướng đến trang lỗi hoặc thông báo lỗi
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // Nếu userId không tồn tại, chuyển hướng đến trang đăng nhập hoặc thông báo lỗi
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
            response.sendRedirect("ProfileServlet");
        } else {
            // Nếu cập nhật không thành công, có thể chuyển hướng đến trang lỗi hoặc thông báo lỗi
            request.setAttribute("updateSuccess", false);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
