package fr.sumwhere.questionnaire.dto;

import fr.sumwhere.questionnaire.model.Formulaire;
import fr.sumwhere.questionnaire.model.ValidationPage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormOptionsDTO {

    private String alias;

    private String validationEmail;

    private Formulaire form;

    private ValidationPage validationPage;

    private String accentColor;

    public FormOptionsDTO(String alias, String validationEmail, Formulaire form, ValidationPage validationPage, String accentColor) {
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.form = form;
        this.validationPage = validationPage;
        this.accentColor = accentColor;
    }
}
