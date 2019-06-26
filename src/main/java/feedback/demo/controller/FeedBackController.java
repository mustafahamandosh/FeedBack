package feedback.demo.controller;

import feedback.demo.mail.FeedbackSender;
import feedback.demo.model.FeedBack;
import feedback.demo.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    private FeedbackSender feedbackSender;

    public FeedBackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }

    @PostMapping
    public void sendFeedback(@Valid @RequestBody FeedBack feedBack,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            String message = null;
            for (FieldError e : errors) {
                message = "";
                message += e.getDefaultMessage();
            }
            throw new ValidationException(message);
        }

        this.feedbackSender.sendFeedback(
                feedBack.getEmail(),
                feedBack.getName(),
                feedBack.getSubject(),
                feedBack.getFeedback());

        this.feedBackService.saveFeedBack(feedBack);
    }

    @GetMapping
    public List<FeedBack> getAllFeedback() {
        return this.feedBackService.listFeedBacks();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.feedBackService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedBack> findById(@PathVariable Long id) {
        return this.feedBackService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedBack> updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedBack feedback) {
        return this.feedBackService.updateFeedback(id, feedback);
    }

    @GetMapping("/{name}/{email}")
    public List<FeedBack> findByNameOrEmail(@PathVariable String name, @PathVariable String email) {
        return feedBackService.findByNameOrEmail(name, email);
    }
}
