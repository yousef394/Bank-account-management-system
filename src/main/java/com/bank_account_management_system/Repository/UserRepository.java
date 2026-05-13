package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.User;

import java.util.ArrayList;

public class UserRepository extends BaseRepository<User> {

    //========== Constructor for file path ========

    public UserRepository() {
        super("src/main/resources/Files/User.txt");
    }


    public User findByUserName(String username) {
        for( User user : getAll() ) {

            if (user.getUsername().equals(username) )
                return user;

        }

        return null;
    }

    public User findByUserNameAndPassword(String username, String password) {

        User user = findByUserName(username);

        if (user != null && password.equals(user.getPassword())) { return user; }

        return null;
    }

    @Override
    public boolean update(User object) {

        boolean result = false;

        ArrayList<User> users = getAll();

        //to clear file
        if(resetFile())
        {
            for(User user : users) {
                //update data
                if(user.getUsername().equals(object.getUsername())) {
                    user =  object;
                    result = true;

                }
                //fill file
                appendLine(format(user));

            }
        }

        return result;


    }

    public boolean delete(String username) {
        boolean result = false;

        ArrayList<User> Users = getAll();

        //to reset file to can fill file again
        if(resetFile()) {
            for (User user :  Users) {
                //skip
                if (user.getUsername().equals(username)) {
                    result = true;
                    continue;
                }

                //fill file
                appendLine(format(user));

            }
        }
        return result;
    }


    //======= Override Methods ========

    @Override
    protected String format(User object) {
        return object.getUsername() +
                separator +
                object.getPassword() +
                separator +
                object.getName() +
                separator +
                object.getEmail();
    }

    @Override
    protected User parse(String line) {

        String[] lineArray = line.split(separator);

        if(lineArray.length<4)
            return null;

        return new User(lineArray[0],lineArray[1],lineArray[2],lineArray[3]);
    }

    @Override
    protected int getId(User object) {

        //there no id so it will return null user
        return -1;
    }
}
