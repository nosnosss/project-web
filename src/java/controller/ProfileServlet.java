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
        if (profile == null) {
            profile = new Profile();
            profile.setUserId(userId);
            profile.setName("");
            profile.setAddress("");
            profile.setPhoneNumber("");
        }

        request.setAttribute("profile", profile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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

        Profile profile = profileService.getProfileByUserId(userId);
        if (profile == null) {
            profile = new Profile();
            profile.setUserId(userId);
            profile.setName(name);
            profile.setAddress(address);
            profile.setPhoneNumber(phoneNumber);

            boolean createSuccess = profileService.createProfile(profile);
            if (createSuccess) {
                request.setAttribute("updateSuccess", true);
            } else {
                request.setAttribute("updateSuccess", false);
            }
        } else {
            profile.setName(name);
            profile.setAddress(address);
            profile.setPhoneNumber(phoneNumber);

            boolean updateSuccess = profileService.updateProfile(profile);
            request.setAttribute("updateSuccess", updateSuccess);
        }

        request.setAttribute("profile", profile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
