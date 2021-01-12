package fr.sumwhere.questionnaire.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Horaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jour, horaires;


    public Horaire() {
    }

    public Horaire(Long id, String jour, String horaires) {
        this.id = id;
        this.jour = jour;
        this.horaires = horaires;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", jour='" + jour + '\'' +
                ", horaires='" + horaires + '\'' +
                '}';
    }
}
