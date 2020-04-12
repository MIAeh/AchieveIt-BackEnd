package com.achieveit.application.enums;

public enum WorkHourStatus {

    WORK_HOUR_PENDING_APPROVAL(0),
    WORK_HOUR_APPROVED(1),
    WORK_HOUR_PENDING_MODIFICATION(2);

    private Integer status;

    WorkHourStatus(Integer status) {
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
