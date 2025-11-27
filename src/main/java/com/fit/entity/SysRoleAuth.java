package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.*;

@Data
public class SysRoleAuth extends BaseEntity<SysRoleAuth> {
    private Long id;

    private Long roleId;

    private Long authId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", authId=").append(authId);
        sb.append("]");
        return sb.toString();
    }
}