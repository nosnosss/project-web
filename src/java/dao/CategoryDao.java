package dao;

import model.Category;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> getAllCategories() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Categories";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            categories.add(category);
        }
        return categories;
    }
}
