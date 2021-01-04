package fr.sumwhere.questionnaire.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //String nom;
    //String prenom;
    String sujet;
    String emailTo;
    String description;
    //String site;
    //String label;
    //String adresse;
    //String codepostale;
    //String ville;
    //long latitude;
    //long longitude;
    //int telephone;
    //Date date;

    public Questionnaire(String emailTo, String sujet,  String description) {
        this.emailTo = emailTo;
        this.sujet = sujet;
        this.description = description;
    }

    public Questionnaire() {
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "emailTo='" + emailTo + '\'' +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
