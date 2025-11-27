package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysDict extends BaseEntity<SysDict> {
    private Long id;

    private Long pid;

    private String name;

    private String code;

    private String sign;

    private String description;

    private Integer sort;

    private Long cuser;

    private Date ctime;

    private Long euser;

    private Date etime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", sign=").append(sign);
        sb.append(", description=").append(description);
        sb.append(", sort=").append(sort);
        sb.append(", cuser=").append(cuser);
        sb.append(", ctime=").append(ctime);
        sb.append(", euser=").append(euser);
        sb.append(", etime=").append(etime);
        sb.append("]");
        return sb.toString();
    }
}