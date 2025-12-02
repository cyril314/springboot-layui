package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsPaper extends BaseEntity<LmsPaper> {
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

    private String uuid;

    /**
     * 业务分类ID
     */
    private Long examTypeId;

    /**
     * 建议答题时间
     */
    private Integer adviceTime;

    /**
     * 考卷名称
     */
    private String title;

    /**
     * 考卷简介
     */
    private String intro;

    /**
     * 考卷说明
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