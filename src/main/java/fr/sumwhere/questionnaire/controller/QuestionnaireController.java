package fr.sumwhere.questionnaire.controller;

import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questionnaire/*")
public class QuestionnaireController {

   @Autowired
   private QuestionnaireService questionnaireService;

    @PostMapping("/envoyer")
    public ResponseEntity<?> envoyerEmail(@RequestBody Questionnaire request) {
        System.out.println(request);
        boolean isEnvoyer = this.questionnaireService.envoyerEmail(request.getEmailTo(),request.getSujet(),request.getDescription(),request.getEmail(),request.getAdresse(),request.getNom(),request.getPrenom(),request.getSite(), request.getLatitude(), request.getLongitude());
        if(isEnvoyer) {

            return new ResponseEntity<>(questionnaireService.sauvegarderQuestionnaire(request), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
