package dao;

import model.Product;
import until.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getAllProducts() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Products";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setImage(resultSet.getString("image"));
            products.add(product);
        }
        return products;
    }
}
