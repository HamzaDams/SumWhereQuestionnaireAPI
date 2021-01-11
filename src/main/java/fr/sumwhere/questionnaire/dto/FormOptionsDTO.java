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

    private String accentColor;

    private String mapId;

    private String logoUrl;

    public FormOptionsDTO(String alias, String validationEmail, Formulaire formulaire, String accentColor, String mapId, String logoUrl) {
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.formulaire = formulaire;
        this.accentColor = accentColor;
        this.mapId = mapId;
        this.logoUrl = logoUrl;
    }
}
