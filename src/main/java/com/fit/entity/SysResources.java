package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysResources extends BaseEntity<SysResources> {
    private Long id;

    private Long pid;

    private Date ctime;

    private String name;

    private String icon;

    private String type;

    private String url;

    private Integer sort;

    private String description;

    private Integer levels;

    private String ismenu;

    private Date etime;

    private Integer enabled;

    private Integer isys;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", ctime=").append(ctime);
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", sort=").append(sort);
        sb.append(", description=").append(description);
        sb.append(", levels=").append(levels);
        sb.append(", ismenu=").append(ismenu);
        sb.append(", etime=").append(etime);
        sb.append(", enabled=").append(enabled);
        sb.append(", isys=").append(isys);
        sb.append("]");
        return sb.toString();
    }
}