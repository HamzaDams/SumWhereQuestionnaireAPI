package fr.sumwhere.questionnaire.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FormOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;
    private String validationEmail;

    @OneToOne(cascade =  CascadeType.ALL)
    private Formulaire formulaire;

    @OneToOne(cascade =  CascadeType.ALL)
    private ValidationPage validationPage;

    private String accentColor;

    public FormOptions() {

    }

    public FormOptions(Long id, String alias, String validationEmail, Formulaire formulaire, ValidationPage validationPage, String accentColor) {
        this.id = id;
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.formulaire = formulaire;
        this.validationPage = validationPage;
        this.accentColor = accentColor;
    }

    @Override
    public String toString() {
        return "FormOptions{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", validationEmail='" + validationEmail + '\'' +
                ", formulaire=" + formulaire +
                ", validationPage=" + validationPage +
                ", accentColor='" + accentColor + '\'' +
                '}';
    }
}
