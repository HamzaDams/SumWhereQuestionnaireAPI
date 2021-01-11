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

    private String accentColor;

    private String mapId;

    private String logoUrl;

    public FormOptions() {

    }

    public FormOptions(Long id, String alias, String validationEmail, Formulaire formulaire, String accentColor, String mapId, String logoUrl) {
        this.id = id;
        this.alias = alias;
        this.validationEmail = validationEmail;
        this.formulaire = formulaire;
        this.accentColor = accentColor;
        this.mapId = mapId;
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "FormOptions{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", validationEmail='" + validationEmail + '\'' +
                ", formulaire=" + formulaire +
                ", accentColor='" + accentColor + '\'' +
                ", mapId='" + mapId + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
