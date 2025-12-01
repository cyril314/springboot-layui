package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsSubject extends BaseEntity<LmsSubject> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private String uuid;

    private Long typeId;

    private String typeName;

    private String title;

    private Long answerId;

    private String notes;

    private Integer version;

    private Integer sort;

    private Boolean enabled;

    private Date etime;

    private Long euser;

    private String content;

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
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", title=").append(title);
        sb.append(", answerId=").append(answerId);
        sb.append(", notes=").append(notes);
        sb.append(", version=").append(version);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}