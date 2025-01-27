package com.goodsoft.internship.gsservletjsp.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ValidationService {

    List<String> validateUserRequest(HttpServletRequest req);

}
