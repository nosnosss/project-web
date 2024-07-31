/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import until.DBConnection;

/**
 *
 * @author boyca
 */
public class ProductDAO {
    public List<Product> getAllProduct() throws SQLException{
        List<Product> products = new ArrayList<>();
        String query = "select * from Products;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setImage(rs.getString("image"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public Product getProductById(int id) throws SQLException{
        Product p = new Product();
        String query = "select * from Products where id=?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public List<Product> getAllProductByCategoryId(int id) throws SQLException{
        List<Product> products = new ArrayList<>();
        String query = "select * from Products where category_id=?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setImage(rs.getString("image"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public void updateProductByProduct(Product p){
        String query = "update Products set name = ?, description = ?, price = ?, category_id = ?, image = ? where id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setBigDecimal(3, p.getPrice());
            stmt.setInt(4, p.getCategory_id());
            stmt.setString(5, p.getImage());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteProductById(int id){
        String query = "delete from Products where id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
