package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
@Transactional
public interface QuestionnaireService {

    Boolean envoyerQuestionnaire(Questionnaire q) throws MessagingException;
    Questionnaire sauvegarderQuestionnaire(Questionnaire q) throws  BusinessResourceException;
    Optional<Questionnaire> findQuestionnaireByAlias(String alias);



}
