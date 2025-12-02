package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsSubject extends BaseEntity<LmsSubject> {
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
     * UUID
     */
    private String uuid;

    /**
     * 考题类型ID
     */
    private Long typeId;

    /**
     * 考题类型名称
     */
    private String typeName;

    /**
     * 题目
     */
    private String title;

    /**
     * 题目描述
     */
    private String content;

    /**
     * 答案ID
     */
    private Long answerId;

    /**
     * 描述
     */
    private String notes;

    /**
     * 版本号
     */
    private Integer version;

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
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", answerId=").append(answerId);
        sb.append(", notes=").append(notes);
        sb.append(", version=").append(version);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}