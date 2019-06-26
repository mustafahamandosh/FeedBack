package feedback.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    @Size(min = 3, message = "nome não pode ser menor que 2 ou maior que 10", max = 10)
    private String name;

    @Column(name = "subject")
    @Size(min = 3, message = "assunto não pode ser menor que 2 ou maior que 30", max = 30)
    private String subject;

    @Email(message = "entre com um endereço de e-mail valido")
    @Column(name = "person_email")
    @Size(min = 3, message = "email não pode ser menor que 2 ou maior que 10", max = 100)
    private String email;

    @Column(name = "feedback")
    @Size(min = 10, message = "feedback não pode ser menor que 10 ou maior que 150", max = 150)
    private String feedback;

    public FeedBack() {
    }
}
