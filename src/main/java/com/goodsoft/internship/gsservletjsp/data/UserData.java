package com.goodsoft.internship.gsservletjsp.data;

import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserData {

    private final ArrayList<User> users = new ArrayList<>();
    private static final UserData userData = new UserData();
    private final AtomicInteger id = new AtomicInteger(1);

    private UserData() {}

    public static UserData getInstance() {
        return userData;
    }

    public int getId() {
        return id.getAndIncrement();
    }

    public List<User> getUsers() {
        return users;
    }

}
