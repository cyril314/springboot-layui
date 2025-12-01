package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsPaper extends BaseEntity<LmsPaper> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private String uuid;

    private Long examTypeId;

    private Integer adviceTime;

    private String title;

    private String intro;

    private String notes;

    private Integer sort;

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
        sb.append(", uuid=").append(uuid);
        sb.append(", examTypeId=").append(examTypeId);
        sb.append(", adviceTime=").append(adviceTime);
        sb.append(", title=").append(title);
        sb.append(", intro=").append(intro);
        sb.append(", notes=").append(notes);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}