package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsSubjectKnow extends BaseEntity<LmsSubjectKnow> {
    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 创建人
     */
    private Long cuser;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 科目名称
     */
    private String name;

    /**
     * 关键字
     */
    private String cruk;

    /**
     * 描述
     */
    private String notes;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 禁用状态: 0-禁用,1-正常
     */
    private Boolean enabled;

    /**
     * 修改时间
     */
    private Date etime;

    /**
     * 修改人
     */
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
        sb.append(", name=").append(name);
        sb.append(", cruk=").append(cruk);
        sb.append(", notes=").append(notes);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}