package com.goodsoft.internship.gsservletjsp.enumeration;

public enum Role {
    ADMIN ("Admin"),
    USER ("User"),
    MODERATOR ("Moderator"),
    EDITOR ("Editor"),
    MANAGER ("Manager");

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
