package com.slack.bot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_stage")
public class UserStage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"

    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "stage_number")
    private int stageNumber;

    @Column(name = "user_id", length = 10485760)
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStage userStage = (UserStage) o;
        return Objects.equals(id, userStage.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
