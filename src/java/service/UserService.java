/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;

/**
 *
 * @author boyca
 */
public class UserService {
    private UserDAO userDao;
    
    public UserService() {
        this.userDao = new UserDAO();
    }
    
    public void lockAcc (int userId){
        userDao.updateLocked(userId, true);
    }
    
    public void unLockAcc (int userId){
        userDao.updateLocked(userId, false);
    }
    
    public User getUserById (int id){
        return userDao.getUserById(id);
    }
}
