package com.RailSwift.Devlopment.Service;

import com.RailSwift.Devlopment.DAO.Interfaces.UserDAO;
import com.RailSwift.Devlopment.Entities.Bookings;
import com.RailSwift.Devlopment.Entities.Role;
import com.RailSwift.Devlopment.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }

    public Users getUserById(int userId,String rawPassword) {
        Users user = userDAO.getUser(userId);
        if(passwordEncoder.matches(rawPassword,user.getPassword()))
        {
            return user;
        }
        else
            throw new RuntimeException("Incorrect Password");
    }
    public Users getUser(int userId) {
        return userDAO.getUser(userId);
    }
    public Users getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public void updateFirstName(int userId, String firstName) {
        userDAO.updateFirstName(userId, firstName);
    }

    public void updateLastName(int userId, String lastName) {
        userDAO.updateLastName(userId, lastName);
    }

    public void updateEmail(int userId, String email) {
        userDAO.updateEmail(userId, email);
    }

    public void updatePhoneNo(int userId, String phoneNo) {
        userDAO.updatePhoneNo(userId, phoneNo);
    }

    public void updatePassword(int userId, String curPassword, String newPassword) throws Exception {
        Users user = userDAO.getUser(userId);
        if(passwordEncoder.matches(curPassword,user.getPassword()))
        {
            userDAO.updatePassword(user,passwordEncoder.encode(newPassword));
        }
        else
            throw new Exception("Incorrect Password");
    }

    public void updateRole(int userId, Role role) {
        userDAO.updateRole(userId, role);
    }
//    Remaining
    public void addBooking(int userId, Bookings booking) {
        userDAO.addBooking(userId, booking);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public void addMultipleUser(List<Users> users) {
        for (Users user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.addMultipleUser(users);
    }
}
