package com.fit.entity;

import com.fit.base.BaseEntity;
import java.util.Date;
import lombok.*;

@Data
public class LmsQuestionsAnswers extends BaseEntity<LmsQuestionsAnswers> {
    private Long id;

    private Date ctime;

    private Long cuser;

    private String uuid;

    private Long questionId;

    private Boolean correct;

    private String answer;

    private Integer score;

    private String notes;

    private Integer sort;

    private Boolean enabled;

    private Date etime;

    private Long euser;

    private String answerContent;

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
        sb.append(", score=").append(score);
        sb.append(", notes=").append(notes);
        sb.append(", sort=").append(sort);
        sb.append(", enabled=").append(enabled);
        sb.append(", etime=").append(etime);
        sb.append(", euser=").append(euser);
        sb.append(", answerContent=").append(answerContent);
        sb.append("]");
        return sb.toString();
    }
}