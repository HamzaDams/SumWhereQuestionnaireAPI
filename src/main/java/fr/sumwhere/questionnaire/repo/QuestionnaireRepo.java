package fr.sumwhere.questionnaire.repo;

import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
    Optional<Questionnaire> findByAlias(String alias);
}
