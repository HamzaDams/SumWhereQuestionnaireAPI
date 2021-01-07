package fr.sumwhere.questionnaire.model;

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

    @OneToOne
    private Formulaire form;

    @OneToOne
    private ValidationPage validationPage;

    private String accentColor;

    public FormOptions() {

    }

    public FormOptions(Long id, String alias, String validationEmail, Formulaire form, ValidationPage validationPage, String accentColor) {
        this.id = id;
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.form = form;
        this.validationPage = validationPage;
        this.accentColor = accentColor;
    }

    @Override
    public String toString() {
        return "FormOptions{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", validationEmail='" + validationEmail + '\'' +
                ", form=" + form +
                ", validationPage=" + validationPage +
                ", accentColor='" + accentColor + '\'' +
                '}';
    }
}
