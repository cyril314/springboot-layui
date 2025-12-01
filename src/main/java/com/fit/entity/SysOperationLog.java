package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysOperationLog extends BaseEntity<SysOperationLog> {
    private Long id;

    private Date ctime;

    private String logType;

    private String logName;

    private Long userId;

    private String className;

    private String method;

    private String succeed;

    private String message;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ctime=").append(ctime);
        sb.append(", logType=").append(logType);
        sb.append(", logName=").append(logName);
        sb.append(", userId=").append(userId);
        sb.append(", className=").append(className);
        sb.append(", method=").append(method);
        sb.append(", succeed=").append(succeed);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}