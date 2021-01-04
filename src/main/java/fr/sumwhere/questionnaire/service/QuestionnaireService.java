package fr.sumwhere.questionnaire.service;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class QuestionnaireService {

    public void envoyerEmail(String sujet, String message, String to){

        String host="smtp.gmail.com";

        //Recup system props
        Properties props = System.getProperties();
        System.out.println("PROPS" + props);

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("test31.compte31@gmail.com", "Motdepasse123!");
            }
        });
        session.setDebug(true);




    }

}
