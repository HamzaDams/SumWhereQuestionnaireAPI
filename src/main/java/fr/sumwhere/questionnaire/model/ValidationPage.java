package fr.sumwhere.questionnaire.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
public class ValidationPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customText;

    @Override
    public String toString() {
        return "ValidationPage{" +
                "id=" + id +
                ", customText='" + customText + '\'' +
                '}';
    }
}

