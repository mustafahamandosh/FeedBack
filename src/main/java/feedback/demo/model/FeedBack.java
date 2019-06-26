package feedback.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Feedback")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @Column(name = "person_name")
    @NotBlank(message = "o nome não pode ser vazio ou nulo")
    @Size(min = 3, message = "nome não pode ser menor que 2 ou maior que 10", max = 10)
    private String name;

    @Column(name = "subject")
    @NotBlank(message = "o assunto não pode ser vazio ou nulo")
    @Size(min = 3, message = "assunto não pode ser menor que 2 ou maior que 30", max = 30)
    private String subject;

    @Email
    @Column(name = "person_email")
    @NotBlank(message = "o email não pode ser vazio ou nulo")
    @Size(min = 3, message = "email não pode ser menor que 2 ou maior que 10", max = 100)
    private String email;

    @Column(name = "feedback")
    @NotBlank(message = "o feedback não pode ser vazio ou nulo")
    @Size(min = 3, message = "feedback não pode ser menor que 2 ou maior que 150", max = 150)
    private String feedback;

    public FeedBack(){

    }
}
