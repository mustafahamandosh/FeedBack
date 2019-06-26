package feedback.demo.service;

import feedback.demo.exception.ResourceNotFoundException;
import feedback.demo.model.FeedBack;
import feedback.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedBackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void saveFeedBack(FeedBack feedBack) {
        feedbackRepository.save(feedBack);
    }

    public List<FeedBack> listFeedBacks() {
        return feedbackRepository.findAll();
    }

    public Map<String, Boolean> deleteById(Long id) {
        FeedBack feedBack = feedbackRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("O id + " + id + "  + que você inseriu não foi encontrado "));
        feedbackRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    public ResponseEntity<FeedBack> findById(Long id) throws ResourceNotFoundException {
        FeedBack feedBack = feedbackRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("O id + " + id + "  + que você inseriu não foi encontrado "));
        return ResponseEntity.ok().body(feedBack);
    }

    public List<FeedBack> findByNameOrEmail(String name, String email) {
        return feedbackRepository.findByNameOrEmail(name, email);
    }

    public ResponseEntity<FeedBack> updateFeedback(Long id, FeedBack feedBackDetails) {
        FeedBack feedBack = feedbackRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("O id + " + id + "  + que você inseriu não foi encontrado "));

        feedBack.setName(feedBackDetails.getName());
        feedBack.setSubject(feedBackDetails.getSubject());
        feedBack.setEmail(feedBackDetails.getEmail());
        feedBack.setFeedback(feedBackDetails.getFeedback());

        feedBack = feedbackRepository.save(feedBack);
        return ResponseEntity.ok(feedBack);
    }
}
