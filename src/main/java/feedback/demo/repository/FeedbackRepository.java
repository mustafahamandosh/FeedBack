package feedback.demo.repository;

import feedback.demo.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {
    List<FeedBack> findByNameOrEmail (String name, String email);
}
