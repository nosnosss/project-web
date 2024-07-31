package dao;

import model.Product;
import until.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> searchProductsByNameOrCategory(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE name LIKE ? OR category_id IN (SELECT id FROM Categories WHERE name LIKE ?)";

        try (Connection jdbcConnection = DBConnection.getConnection();
             PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            String searchQuery = "%" + query + "%";
            statement.setString(1, searchQuery);
            statement.setString(2, searchQuery);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchProductsByDescriptionOrPrice(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE description LIKE ? OR price LIKE ?";

        try (Connection jdbcConnection = DBConnection.getConnection();
             PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            String searchQuery = "%" + query + "%";
            statement.setString(1, searchQuery);
            statement.setString(2, searchQuery);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
}
