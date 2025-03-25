package com.RailSwift.Devlopment.DAO.Interfaces;

import com.RailSwift.Devlopment.Entities.Bookings;
import com.RailSwift.Devlopment.Entities.Role;
import com.RailSwift.Devlopment.Entities.Users;

import java.util.List;

public interface UserDAO {
    public void addUser(Users user);

    public Users getUser(int userId);

    public Users getUserByEmail(String email);

    public void updateFirstName(int userId, String firstName);

    public void updateLastName(int userId, String lastName);

    public void updateEmail(int userId, String email);

    public void updatePhoneNo(int userId, String phoneNo);

    public void updatePassword(Users user, String password);

    public void updateRole(int userId, Role role);

    public void addBooking(int userId, Bookings booking);

    public void deleteUser(int userId);

    void addMultipleUser(List<Users> users);
}
