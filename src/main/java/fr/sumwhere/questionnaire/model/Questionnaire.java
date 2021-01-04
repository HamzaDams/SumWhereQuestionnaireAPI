package fr.sumwhere.questionnaire.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class Questionnaire {
    String nom;
    String prenom;
    String email;
    String description;
    String site;
    String label;
    String adresse;
    long latitude;
    long longitude;
    int telephone;
    Date date;
}
