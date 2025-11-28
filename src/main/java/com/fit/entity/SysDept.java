package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysDept extends BaseEntity<SysDept> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private Long pid;

    private String pids;

    private String simpleName;

    private String fullName;

    private String notes;

    private Integer version;

    private Integer sort;

    private Date etime;

    private Long euser;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ctime=").append(ctime);
        sb.append(", cuser=").append(cuser);
        sb.append(", pid=").append(pid);
        sb.append(", pids=").append(pids);
        sb.append(", simpleName=").append(simpleName);
        sb.append(", fullName=").append(fullName);
        sb.append(", notes=").append(notes);
        sb.append(", version=").append(version);
        sb.append(", sort=").append(sort);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}