package fr.sumwhere.questionnaire.dto;

import fr.sumwhere.questionnaire.model.Formulaire;
import fr.sumwhere.questionnaire.model.ValidationPage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FormOptionsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alias;

    private String validationEmail;

    private Formulaire formulaire;

    private ValidationPage validationPage;

    private String accentColor;

    public FormOptionsDTO(String alias, String validationEmail, Formulaire formulaire, ValidationPage validationPage, String accentColor) {
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.formulaire = formulaire;
        this.validationPage = validationPage;
        this.accentColor = accentColor;
    }
}
