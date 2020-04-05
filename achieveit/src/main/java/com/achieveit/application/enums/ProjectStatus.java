package com.achieveit.application.enums;

public enum ProjectStatus {

    APPLY_FOR_APPROVAL(0),
    APPROVED(1),
    REJECTED(2),
    IN_PROGRESS(3),
    DELIVERED(4),
    ENDED(5),
    ARCHIVED(6);

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
