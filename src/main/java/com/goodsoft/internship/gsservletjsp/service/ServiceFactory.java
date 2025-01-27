package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.service.impl.ServiceFactoryImpl;

public interface ServiceFactory {
    ValidationService getValidationServiceInstance();

    UserService getUserServiceInstance();

    static ServiceFactory newInstance() {
        return new ServiceFactoryImpl();
    }
}
