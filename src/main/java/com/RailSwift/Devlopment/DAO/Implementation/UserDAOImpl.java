package com.RailSwift.Devlopment.DAO.Implementation;

import com.RailSwift.Devlopment.DAO.Interfaces.UserDAO;
import com.RailSwift.Devlopment.Entities.Bookings;
import com.RailSwift.Devlopment.Entities.Role;
import com.RailSwift.Devlopment.Entities.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    EntityManager entityManager;

    @Autowired
    public UserDAOImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(Users user) {
        entityManager.persist(user);
    }

    @Override
    public Users getUser(int userId) {
        return entityManager.find(Users.class, userId);
    }

    @Override
    public Users getUserByEmail(String email) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateFirstName(int userId, String firstName) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        user.setFirstName(firstName);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updateLastName(int userId, String lastName) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        user.setLastName(lastName);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updateEmail(int userId, String email) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        user.setEmail(email);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updatePhoneNo(int userId, String phoneNo) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        user.setPhoneNo(phoneNo);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updatePassword(Users user, String password) {
        user.setPassword(password);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updateRole(int userId, Role role) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        user.setRole(role);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void addBooking(int userId, Bookings booking) {
        Users user = entityManager.find(Users.class, userId);
        user.addToBooking(booking);
        booking.setUser(user);
        entityManager.persist(booking);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class);
        query.setParameter("userId", userId);
        Users user = query.getSingleResult();
        List<Bookings> bookingsList = user.getBookings();
        for (Bookings booking : bookingsList) {
            booking.setUser(null);
            entityManager.merge(booking);
        }
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void addMultipleUser(List<Users> users) {
        for (Users user : users) {
            entityManager.persist(user);
        }
    }
}
