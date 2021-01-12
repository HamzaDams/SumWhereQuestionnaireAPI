package fr.sumwhere.questionnaire.repo;

import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {
    Optional<Questionnaire> findByDemandeAlias(String alias);

    @Modifying
    @Query(value = "update Questionnaire q set q.status = ? where q.id = ?",
            nativeQuery = true)
    int updateQuestionnaireStatus(Integer status, Long id);
}
