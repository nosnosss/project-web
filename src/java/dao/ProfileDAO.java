/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Profile;
import until.DBConnection;

/**
 *
 * @author boyca
 */
public class ProfileDAO {
    public Profile getProfileOfUserById(int userId) throws SQLException {
        Profile profile = new Profile();
        String query = "SELECT * FROM Profiles WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                profile.setId(rs.getInt("id"));
                profile.setUserId(rs.getInt("user_id"));
                profile.setName(rs.getString("name"));
                profile.setAddress(rs.getString("address"));
                profile.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
