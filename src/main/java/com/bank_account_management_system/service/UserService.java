package com.bank_account_management_system.service;

import com.bank_account_management_system.Repository.UserRepository;
import com.bank_account_management_system.model.User;

import java.util.ArrayList;

public class UserService {

    private final  UserRepository userRepo = new UserRepository();
    private final User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public UserService(User currentUser) {
        if(currentUser == null
        || UserService.findByUserNameAndPassword(
                currentUser.getUsername(), currentUser.getPassword()) == null){
            throw new NullPointerException("UserService currentUser is null");
        }

        this.currentUser = currentUser;
    }

    public  boolean createUser(User user) {

        if (user == null) { return false; }

        //Username should be Unique
        if (userRepo.find(user.getUsername()) != null) { return  false; }

        return userRepo.add(user);

    }

    public  boolean delete(String userName) {

        return userRepo.delete(userName);
    }

    public static User findByUserNameAndPassword(String userName , String password) {

     // it is static
     User object = new UserRepository().find(userName);

     if(object!=null && object.getPassword().equals(password)) { return object; }

     return null;
    }

    public  boolean saveUser(User user) {
        if  (user == null) { return false; }

        return userRepo.update(user);
    }

    public  ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

       if( users.addAll(userRepo.getAll()) )
        return userRepo.getAll();

       return users;
    }

}
