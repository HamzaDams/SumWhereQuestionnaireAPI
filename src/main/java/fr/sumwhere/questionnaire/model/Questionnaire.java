package fr.sumwhere.questionnaire.model;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nom;
    String prenom;
    String sujet;
    String emailTo;
    String email;
    String description;
    String site;

    @OneToMany(cascade = CascadeType.ALL)
    List<Label> label;

    String adresse;
    int codepostale;
    String ville;
    float latitude;
    float longitude;
    int telephone;
    Date date;


    public Questionnaire() {
    }

    public Questionnaire(Long id, String nom, String prenom, String sujet, String emailTo, String email, String description, String site, List<Label> label, String adresse, int codepostale, String ville, float latitude, float longitude, int telephone, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sujet = sujet;
        this.emailTo = emailTo;
        this.email = email;
        this.description = description;
        this.site = site;
        this.label = label;
        this.adresse = adresse;
        this.codepostale = codepostale;
        this.ville = ville;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telephone = telephone;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sujet='" + sujet + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", description='" + description + '\'' +
                ", site='" + site + '\'' +
                ", codepostale=" + codepostale +
                ", ville='" + ville + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", telephone=" + telephone +
                ", date=" + date +
                '}';
    }
}
