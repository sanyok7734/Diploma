package com.quoteoftheday.raccoonapps.diploma.managers;

import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class  UserManager {

    private static UserManager userManager = new UserManager();

    private List<User> users = new ArrayList<>();

    private UserManager(){
        users.add(new User("Administrator", "1", "admin", "admin"));
        users.add(new User("Expert", "1", "expert", "expert"));
        users.add(new User("Leonardo", "", "leonard", null));
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getLoginUsers() {
        List<String> strings = new ArrayList<>();

        for (User user : users) {
            strings.add(user.getLogin());
        }

        return strings;
    }

}
