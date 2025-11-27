package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysOperationLog extends BaseEntity<SysOperationLog> {
    private Long id;

    private String logType;

    private String logName;

    private Long userId;

    private String className;

    private Date ctime;

    private String succeed;

    private String method;

    private String message;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logType=").append(logType);
        sb.append(", logName=").append(logName);
        sb.append(", userId=").append(userId);
        sb.append(", className=").append(className);
        sb.append(", ctime=").append(ctime);
        sb.append(", succeed=").append(succeed);
        sb.append(", method=").append(method);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}