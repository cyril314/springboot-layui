package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsTop extends BaseEntity<LmsTop> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private Integer model;

    private String title;

    private Long imgId;

    private Integer mold;

    private Integer visits;

    private Integer sort;

    private Boolean enabled;

    private Date etime;

    private Long euser;

    private String content;

    private String url;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ctime=").append(ctime);
        sb.append(", cuser=").append(cuser);
        sb.append(", model=").append(model);
        sb.append(", title=").append(title);
        sb.append(", imgId=").append(imgId);
        sb.append(", mold=").append(mold);
        sb.append(", visits=").append(visits);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append(", content=").append(content);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}