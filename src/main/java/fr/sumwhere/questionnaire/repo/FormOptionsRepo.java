package fr.sumwhere.questionnaire.repo;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.model.FormOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormOptionsRepo extends JpaRepository<FormOptions, Long> {
    Optional<FormOptionsDTO> findByAlias(String aliasParam);
}
