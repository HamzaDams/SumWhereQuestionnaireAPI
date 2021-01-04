package fr.sumwhere.questionnaire.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public interface QuestionnaireService {

    public boolean envoyerEmail(String to, String sujet, String texte);

}
