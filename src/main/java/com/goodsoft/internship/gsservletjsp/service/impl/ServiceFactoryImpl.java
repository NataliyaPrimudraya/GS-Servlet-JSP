package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.dao.impl.InMemoryUserDao;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import com.goodsoft.internship.gsservletjsp.service.ValidationService;

public class ServiceFactoryImpl implements ServiceFactory {

    private ValidationService validationService;

    @Override
    public ValidationService getValidationServiceInstance() {
        if (validationService == null) {
            this.validationService = new ValidationServiceImpl(getUserServiceInstance());
        }
        return validationService;
    }

    private UserService userService;

    @Override
    public UserService getUserServiceInstance() {
        if (userService == null) {
            this.userService = new UserServiceImpl(getUserDaoInstance());
        }
        return userService;
    }

    private UserDao userDao;

    private UserDao getUserDaoInstance() {
        if (userDao == null) {
            this.userDao = new InMemoryUserDao();
        }
        return userDao;
    }

}
