package com.achieveit.application.enums;

public enum MemberRoles {

    PROJECT_MANAGER(0),
    QA(1),
    EPG(2),
    DEVELOPER(3),
    DEVELOPER_LEADER(4),
    TESTER(5),
    TESTER_LEADER(6),
    PROPERTY_MANAGER(7);

    private Integer role;

    MemberRoles(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "MemberRoles{" +
                "role=" + role +
                '}';
    }
}
