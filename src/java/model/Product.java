/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author boyca
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int category_id;
    private String image;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public BigDecimal getPrice(){
        return this.price;
    }
    
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    
    public int getCategory_id(){
        return this.category_id;
    }
    
    public void setCategory_id(int category_id){
        this.category_id = category_id;
    }
    
    public String getImage(){
        return this.image;
    }
    
    public void setImage(String image){
        this.image = image;
    }
}
