package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.model.FormOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public interface FormOptionsService {
    FormOptions saveFormOptions(FormOptions formOptions);
    Optional<FormOptionsDTO> findFormOptionsByAlias(String alias);
    void deleteFormOptions(Long id);
    Collection<FormOptions> getAllForms();
}
