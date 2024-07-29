package service;

import java.sql.SQLException;
import dao.ProfileDAO;
import model.Profile;

public class ProfileService {
    private ProfileDAO profileDAO;

    public ProfileService() {
        this.profileDAO = new ProfileDAO();
    }

    public Profile getProfileByUserId(int userId) {
        try {
            return profileDAO.getProfileByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProfile(Profile profile) {
        try {
            return profileDAO.updateProfile(profile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
