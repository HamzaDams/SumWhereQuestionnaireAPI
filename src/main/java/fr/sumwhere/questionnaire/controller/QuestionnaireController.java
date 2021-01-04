package fr.sumwhere.questionnaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionnaireController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/envoyerEmail")
    public String envoyerEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("hddesignx@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        this.emailSender.send(message);

        return "Email Sent!";
    }
}
