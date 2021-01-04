package fr.sumwhere.questionnaire.controller;

import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mail/")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/envoyer")
    public ResponseEntity<?> envoyerEmail(@RequestBody Questionnaire request) {
        System.out.println(request);
        boolean isEnvoyer = this.questionnaireService.envoyerEmail(request.getEmailTo(),request.getSujet(),request.getDescription());
        if(isEnvoyer) {
            return  ResponseEntity.ok("Envoyer");
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
