/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author boyca
 */
public class CategoryService {
    private CategoryDAO categoryDao;
    private ProductDAO productDao;
    
    public CategoryService(){
        this.categoryDao = new CategoryDAO();
        this.productDao = new ProductDAO();
    }
    
    public List<Category> listAllCategory() throws SQLException{
        return categoryDao.getAllCategory();
    }
    
    public Category getCategoryDetail(int id){
        return categoryDao.getCategoryById(id);
    }
    public void updateCategory(int id, String name){
        Category c = new Category();
        c.setId(id);
        c.setName(name);
        categoryDao.updateCategoryByCategory(c);
    }
    
    public void deleteCategory(int id) throws SQLException{
        List<Product> lp = new ArrayList<>();
        lp = productDao.getAllProductByCategoryId(id);
        for(Product p : lp){
            productDao.deleteProductById(p.getId());
        }
        categoryDao.deleteCategoryById(id);
    }
}
