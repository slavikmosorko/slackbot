package com.slack.bot.repositories;

import com.slack.bot.models.UserStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStageRepository extends JpaRepository<UserStage, Long> {

    @Query(value = "SELECT DISTINCT * FROM user_stage as us WHERE us.user_id = :userId",
            nativeQuery = true)
    List<UserStage> getUserStage(@Param("userId") String userId);
}
