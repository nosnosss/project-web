/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProfileDAO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Profile;
import model.AccInfo;

/**
 *
 * @author boyca
 */
public class AccInfoService {
    private ProfileDAO profileDao;
    private UserDAO userDao;
    
    public AccInfoService() {
        this.profileDao = new ProfileDAO();
        this.userDao = new UserDAO();
    }
    
    public List<AccInfo> createListAccInfo() throws SQLException{
        List<AccInfo> accs = new ArrayList<>();
        List<User> users = userDao.getAllMemberAccount();
        for(User u : users){
            Profile p = profileDao.getProfileOfUserById(u.getId());
            AccInfo a = new AccInfo();
            a.setUserId(u.getId());
            a.setName(p.getName());
            a.setUsername(u.getUsername());
            a.setEmail(u.getEmail());
            a.setRole(u.getRole());
            if(!u.isLocked()){
                a.setIsActive(true);
            } else {
                a.setIsActive(false);
            }
            accs.add(a);
        }
        return accs;
    }
}
