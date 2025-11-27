package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysUser extends BaseEntity<SysUser> {
    private Long id;

    private Long rid;

    private Date ctime;

    private String name;

    private String username;

    private String password;

    private String desc;

    private Date etime;

    private Boolean enabled;

    private Integer isys;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rid=").append(rid);
        sb.append(", ctime=").append(ctime);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", desc=").append(desc);
        sb.append(", etime=").append(etime);
        sb.append(", enabled=").append(enabled);
        sb.append(", isys=").append(isys);
        sb.append("]");
        return sb.toString();
    }
}