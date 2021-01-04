package fr.sumwhere.questionnaire.repo;

import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
}
