package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.exception.BusinessResourceException;
import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.repo.FormOptionsRepo;
import fr.sumwhere.questionnaire.repo.QuestionnaireRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);
    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    @Autowired
    private FormOptionsRepo formOptionsRepo;

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public QuestionnaireServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public Boolean envoyerQuestionnaire(Questionnaire q) {
        String aliasName = q.getAlias();
        Optional<FormOptionsDTO> formOptionsContent = formOptionsRepo.findByAlias(aliasName);
        String color = formOptionsContent.get().getAccentColor();
        String logoUrl = formOptionsContent.get().getLogoUrl();

        String contenue = generateContenueMail(q, color, logoUrl);
        try {
            sendMail(q.getSujet(),contenue,formOptionsContent.get().getValidationEmail());
            generateContenueMail(q, color, logoUrl);
        } catch (MessagingException e) {
            logger.error("Erreur lors de l'envoie du questionnaire par mail - ",e);
            return false;
        }
        return true;
    }

    private String generateContenueMail(Questionnaire q, String color, String logoUrl){
        Context context = new Context();
        String coords = q.getLatitude() +","+ q.getLongitude();
        String urlMap = "http://maps.google.com/maps/api/staticmap?center=" + coords + "&zoom=15&markers=" + coords + "|" + coords + "&path=color:0x0000FF80|weight:5|" + coords + "&size=460x460&key=AIzaSyD-25Q3gSx-vVlsmdfXtgEGc37bqwmwKjo";
        String urlSuccess = "http://localhost:4200/success/" + q.getAlias();
        context.setVariable("q", q);
        context.setVariable("urlMap", urlMap);
        context.setVariable("urlSuccess", urlSuccess);
        context.setVariable("color", color);
        context.setVariable("logoUrl", logoUrl);
        String process = templateEngine.process("questionnaireMailTemplate.html", context);
        return process;
    }

    private void sendMail(String sujet, String contenue, String mailTo) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(sujet);
        helper.setText(contenue, true);
        helper.setTo(mailTo);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public Questionnaire sauvegarderQuestionnaire(Questionnaire q) {
        Questionnaire qRecu = questionnaireRepo.save(q);
        return qRecu;
    }

    @Override
    public Optional<Questionnaire> findQuestionnaireByAlias(String alias) {
        Optional<Questionnaire> questionnaireFound = questionnaireRepo.findByAlias(alias);
        return questionnaireFound;
    }
}
