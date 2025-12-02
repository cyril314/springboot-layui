package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsSubjectAnswers extends BaseEntity<LmsSubjectAnswers> {
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
     * 考题ID
     */
    private Long questionId;

    /**
     * 是否正确
     */
    private Boolean correct;

    /**
     * 答案
     */
    private String answer;

    /**
     * 答案结果
     */
    private String answerContent;

    /**
     * 得分权重
     */
    private Integer score;

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
        sb.append(", uuid=").append(uuid);
        sb.append(", questionId=").append(questionId);
        sb.append(", correct=").append(correct);
        sb.append(", answer=").append(answer);
        sb.append(", answerContent=").append(answerContent);
        sb.append(", score=").append(score);
        sb.append(", notes=").append(notes);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append("]");
        return sb.toString();
    }
}