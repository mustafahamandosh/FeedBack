package feedback.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    private String name;

    @Column(name = "subject")
    private String subject;

    @Email
    @Column(name = "person_email")
    private String email;

    @Column(name = "feedback")
    private String feedback;

    public FeedBack(){

    }
}
