package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.service.impl.DBConnectionManagerImpl;
import com.goodsoft.internship.gsservletjsp.service.impl.ServiceFactoryImpl;

public interface ServiceFactory {

    ValidationService getValidationServiceInstance();

    UserService getUserServiceInstance();

    DBConnectionManager getDBConnectionManagerInstance();

    static ServiceFactory newInstance() {
        return ServiceFactoryImpl.getInstance();
    }
}
