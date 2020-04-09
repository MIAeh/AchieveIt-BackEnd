package com.achieveit.application.enums;

public enum ProjectStatus {

    APPLY_FOR_APPROVAL(0),
    APPROVED(1),
    IN_PROGRESS(2),
    DELIVERED(3),
    ENDED(4),
    ARCHIVED(5),
    REJECTED(6);

    private Integer status;

    ProjectStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ProjectStatus{" +
                "status=" + status +
                '}';
    }
}
