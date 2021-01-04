package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface QuestionnaireService {

    boolean envoyerEmail(String to, String sujet, String texte, String email, String adresse, String nom, String prenom, String site, float latitude, float longitude) throws BusinessResourceException;
    Questionnaire sauvegarderQuestionnaire(Questionnaire q) throws  BusinessResourceException;

}
