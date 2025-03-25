package com.RailSwift.Devlopment.Controller;

import com.RailSwift.Devlopment.Entities.Role;
import com.RailSwift.Devlopment.Entities.Users;
import com.RailSwift.Devlopment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/RailSwift/User")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/UserDetails/NewUser")
    public Users addUser(@RequestBody Users user) {
        userService.addUser(user);
        return userService.getUserByEmail(user.getEmail());
    }

    @PostMapping("/UserDetails/MultipleNewUser")
    public List<Users> addMultipleUser(@RequestBody List<Users> users) {
        userService.addMultipleUser(users);
        List<Users> usersList = new ArrayList<>();
        for (Users user : users) {
            usersList.add(userService.getUserByEmail(user.getEmail()));
        }
        return usersList;
    }

    @GetMapping("/UserDeatils/{userId}")
    public Users getUserDetails(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/UserDetails/Email/{email}")
    public Users getUserDetailsByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/UserDetails/UpdateFirstName/{userId}/{firstName}")
    public Users updateFirstName(@PathVariable int userId, @PathVariable String firstName) {
        userService.updateFirstName(userId, firstName);
        return userService.getUser(userId);
    }

    @PutMapping("/UserDetails/UpdateLastName/{userId}/{lastName}")
    public Users updateLastName(@PathVariable int userId, @PathVariable String lastName) {
        userService.updateLastName(userId, lastName);
        return userService.getUser(userId);
    }

    @PutMapping("/UserDetails/UpdateEmail/{userId}/{email}")
    public Users updateEmail(@PathVariable int userId, @PathVariable String email) {
        userService.updateEmail(userId, email);
        return userService.getUser(userId);
    }

    @PutMapping("/UserDetails/UpdatePhoneNo/{userId}/{phoneNo}")
    public Users updatePhoneNo(@PathVariable int userId, @PathVariable String phoneNo) {
        userService.updatePhoneNo(userId, phoneNo);
        return userService.getUser(userId);
    }


    @PutMapping("/UserDetails/UpdatePassWord/{userId}/{curPassword}/{newPassword}")
    public Users updatePassword(@PathVariable int userId, @PathVariable String curPassword, @PathVariable String newPassword) throws Exception {
        userService.updatePassword(userId, curPassword, newPassword);
        return userService.getUser(userId);
    }

    @PutMapping("/UserDetails/UpdateRole/{userId}/{role}")
    public Users updateRole(@PathVariable int userId, @PathVariable Role role) {
        userService.updateRole(userId, role);
        return userService.getUser(userId);
    }

    @DeleteMapping("/UserDetails/{userId}")
    public String deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return "User Deleted Successfully";
        } catch (Exception e) {
            return "User Not Found";
        }
    }
}
