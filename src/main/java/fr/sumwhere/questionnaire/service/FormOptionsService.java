package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.model.FormOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface FormOptionsService {
    FormOptions saveFormOptions(FormOptions formOptions);
}
