package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.model.Questionnaire;
import fr.sumwhere.questionnaire.repo.QuestionnaireRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);
    @Autowired
    private QuestionnaireRepo questionnaireRepo;



    @Override
    public boolean envoyerEmail(String to, String sujet, String texte) {

        boolean isEnvoyer=false;

        String host="smtp.gmail.com";

        //Recup system props
        Properties props = System.getProperties();
        System.out.println("PROPS" + props);

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.auth","true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("test31.compte31@gmail.com", "Motdepasse123!");
            }
        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom("test31.compte31@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sujet);
            message.setText(texte);

            Transport.send(message);

            System.out.println(message);

            isEnvoyer=true;
        }catch(Exception e) {
            logger.error("L'envoi du questionnaire n'a pas pu être effectué" + e);
        }
        return isEnvoyer;
    }

    @Override
    public Questionnaire sauvegarderQuestionnaire(Questionnaire q) {
        Questionnaire qRecu = questionnaireRepo.save(q);
        return qRecu;
    }


}
