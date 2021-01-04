package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.model.Questionnaire;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface QuestionnaireService {

    boolean envoyerEmail(String to, String sujet, String texte);
    Questionnaire sauvegarderQuestionnaire(Questionnaire q);

}
