package fr.sumwhere.questionnaire.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
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
            System.out.println(e);
        }
        return isEnvoyer;
    }
}
