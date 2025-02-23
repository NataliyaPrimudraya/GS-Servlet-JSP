package com.goodsoft.internship.gsservletjsp.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    MODERATOR,
    EDITOR,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
