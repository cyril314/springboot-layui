package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class SysUser extends BaseEntity<SysUser> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private Long rid;

    private String name;

    private String username;

    private String password;

    private String notes;

    private Boolean enabled;

    private Integer isys;

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
        sb.append(", rid=").append(rid);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", notes=").append(notes);
        sb.append(", enabled=").append(enabled);
        sb.append(", isys=").append(isys);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}