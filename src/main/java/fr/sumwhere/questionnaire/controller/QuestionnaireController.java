package fr.sumwhere.questionnaire.controller;

import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.FormOptions;
import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questionnaire/*")
public class QuestionnaireController {

   @Autowired
   private QuestionnaireService questionnaireService;

    //A voir quoi mettre pour l'api
    @PostMapping(value = "/envoyer")
    @ResponseBody
    public ResponseEntity<?> sendMail(@RequestBody Questionnaire q) throws BusinessResourceException, MessagingException {
        if(questionnaireService.envoyerQuestionnaire(q)){
            return new ResponseEntity<>(questionnaireService.sauvegarderQuestionnaire(q), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status/{valid}")
    public ResponseEntity<?> updateStatus(@RequestParam(value = "valid", required = true) Enum<Questionnaire.Status> status) {
        Enum<Questionnaire.Status> qUpdate = questionnaireService.updateStatus(status);

        return new ResponseEntity<>(qUpdate, HttpStatus.OK);
    }

}
