package com.achieveit.application.enums;

public enum UserRoles {

    PROJECT_MANAGER(0),
    PROJECT_MONITOR(1),
    EPG_LEADER(2),
    QA_MANAGER(3),
    SYSTEM_CONFIGURATION_MANAGER(4);

    private Integer role;

    UserRoles(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "role=" + role +
                '}';
    }
}
