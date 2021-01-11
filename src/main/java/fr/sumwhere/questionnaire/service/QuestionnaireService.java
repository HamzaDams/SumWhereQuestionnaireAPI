package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@Transactional
public interface QuestionnaireService {

    Boolean envoyerQuestionnaire(Questionnaire q) throws MessagingException;
    Questionnaire sauvegarderQuestionnaire(Questionnaire q) throws  BusinessResourceException;
    Enum<Questionnaire.Status> updateStatus(Enum<Questionnaire.Status> status);

}
