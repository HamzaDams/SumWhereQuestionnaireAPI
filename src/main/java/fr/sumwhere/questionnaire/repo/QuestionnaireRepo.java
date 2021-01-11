package fr.sumwhere.questionnaire.repo;

import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {

    @Query("update Questionnaire q set q.status = ?1")
    void updateStatus(Enum<Questionnaire.Status> status);
}
