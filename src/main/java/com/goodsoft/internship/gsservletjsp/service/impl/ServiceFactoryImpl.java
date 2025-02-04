package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.dao.impl.InMemoryUserDao;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import com.goodsoft.internship.gsservletjsp.service.ValidationService;

public class ServiceFactoryImpl implements ServiceFactory {

    private static final ServiceFactory serviceFactory = new ServiceFactoryImpl();
    private ValidationService validationService;

    private ServiceFactoryImpl() {}

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    @Override
    public ValidationService getValidationServiceInstance() {
        if (validationService == null) {
            ValidationServiceImpl.getInstance().setUserService(getUserServiceInstance());
            this.validationService = ValidationServiceImpl.getInstance();
        }
        return validationService;
    }

    private UserService userService;

    @Override
    public UserService getUserServiceInstance() {
        if (userService == null) {
            UserServiceImpl.getInstance().setUserDao(getUserDaoInstance());
            this.userService = UserServiceImpl.getInstance();
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
