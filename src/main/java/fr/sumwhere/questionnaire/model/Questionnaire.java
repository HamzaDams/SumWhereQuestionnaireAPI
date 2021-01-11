package fr.sumwhere.questionnaire.model;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Questionnaire {
    public enum Status {
        VALIDE,ATTENTE,REFUSER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nom;
    String prenom;
    String sujet;
    String email;
    String description;
    String site;
    String alias;

    String placeName;

    @OneToMany(cascade = CascadeType.ALL)
    List<Label> label;

    String adresse;
    int codepostale;
    String ville;
    String latitude;
    String longitude;
    String telephone;
    Date date;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ATTENTE;

    public Questionnaire() {
    }

    public Questionnaire(Long id, String nom, String prenom, String sujet, String email, String description, String site, String alias, String placeName, List<Label> label, String adresse, int codepostale, String ville, String latitude, String longitude, String telephone, Date date, Status status) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sujet = sujet;
        this.email = email;
        this.description = description;
        this.site = site;
        this.alias = alias;
        this.placeName = placeName;
        this.label = label;
        this.adresse = adresse;
        this.codepostale = codepostale;
        this.ville = ville;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telephone = telephone;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sujet='" + sujet + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", site='" + site + '\'' +
                ", alias='" + alias + '\'' +
                ", placeName='" + placeName + '\'' +
                ", label=" + label +
                ", adresse='" + adresse + '\'' +
                ", codepostale=" + codepostale +
                ", ville='" + ville + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", telephone='" + telephone + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}

