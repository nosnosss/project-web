package dao;

import java.sql.*;
import model.Profile;
import until.DBConnection;

public class ProfileDAO {
    // Get profile by user ID
    public Profile getProfileByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM Profiles WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Profile profile = new Profile();
                profile.setId(rs.getInt("id"));
                profile.setUserId(rs.getInt("user_id"));
                profile.setName(rs.getString("name"));
                profile.setAddress(rs.getString("address"));
                profile.setPhoneNumber(rs.getString("phone_number"));
                return profile;
            }
        }
        return null;
    }

    // Update profile
    public boolean updateProfile(Profile profile) throws SQLException {
        String query = "UPDATE Profiles SET name = ?, address = ?, phone_number = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, profile.getName());
            stmt.setString(2, profile.getAddress());
            stmt.setString(3, profile.getPhoneNumber());
            stmt.setInt(4, profile.getUserId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
