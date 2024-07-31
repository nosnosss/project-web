/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProductDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import model.Product;

/**
 *
 * @author boyca
 */
public class ProductService {
    private ProductDAO productDao;
    
    public ProductService(){
        this.productDao = new ProductDAO();
    }
    
    public List<Product> listAllProducts() throws SQLException{
        return productDao.getAllProduct();
    }
    
    public Product getProductDetail(int id) throws SQLException{
        return productDao.getProductById(id);
    }
    
    public void updateProduct(int id, String name, String description, BigDecimal price, int category_id, String img) throws SQLException{
        Product p = new Product();

        if(img == null || img.replaceAll("\\s+", "") == ""){
            Product old_p = new Product();
            old_p = productDao.getProductById(id);
            img = old_p.getImage();
        }
        
        p.setId(id);
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setCategory_id(category_id);
        p.setImage(img);
        productDao.updateProductByProduct(p);
    }
    public void deleteProduct(int id){
        productDao.deleteProductById(id);
    }
}
