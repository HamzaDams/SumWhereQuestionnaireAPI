package fr.sumwhere.questionnaire.controller;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.FormOptions;
import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.repo.QuestionnaireRepo;
import fr.sumwhere.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questionnaire/*")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    //A voir quoi mettre pour l'api
    @PostMapping(value = "/envoyer")
    @ResponseBody
    public ResponseEntity<?> sendMail(@RequestBody Questionnaire q) throws BusinessResourceException, MessagingException {
        if (questionnaireService.envoyerQuestionnaire(q)) {
            return new ResponseEntity<>(questionnaireService.sauvegarderQuestionnaire(q), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{demandeAlias}")
    public Optional<Questionnaire> findQuestionnaireByAlias(@PathVariable(value = "demandeAlias") String demandeAlias) {
        return questionnaireService.findQuestionnaireByAlias(demandeAlias);
    }

    @GetMapping("/changeStatus/{demandeAlias}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable(value = "demandeAlias") String demandeAlias, @PathVariable(value = "status") Integer status) {
        long idFound = questionnaireRepo.findByDemandeAlias(demandeAlias).get().getId();

        if(status <= 2){
            return new ResponseEntity<>(questionnaireRepo.updateQuestionnaireStatus(status, idFound), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
