package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsExamType extends BaseEntity<LmsExamType> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private Long pid;

    private Integer mold;

    private String name;

    private String notes;

    private Integer sort;

    private Integer adminAuth;

    private Integer gradeAuth;

    private Integer queryAuth;

    private Integer superAuth;

    private Boolean enabled;

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
        sb.append(", mold=").append(mold);
        sb.append(", name=").append(name);
        sb.append(", notes=").append(notes);
        sb.append(", sort=").append(sort);
        sb.append(", adminAuth=").append(adminAuth);
        sb.append(", gradeAuth=").append(gradeAuth);
        sb.append(", queryAuth=").append(queryAuth);
        sb.append(", superAuth=").append(superAuth);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}