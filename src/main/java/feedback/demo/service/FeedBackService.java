package feedback.demo.service;

import feedback.demo.exception.ResourceNotFoundException;
import feedback.demo.model.FeedBack;
import feedback.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }

    public FeedBack findById(Long id) {
        return  feedbackRepository.findById(id).get();
    }

    public List<FeedBack> findByNameOrEmail(String name, String email) {
        return  feedbackRepository.findByNameOrEmail(name, email);
    }

    public void updateFeedback(FeedBack feedBack) {
        if(feedbackRepository.save(feedBack) != null){
            System.out.println("Already registered.");
        }else {
            feedbackRepository.save(feedBack);
        }
    }
}
