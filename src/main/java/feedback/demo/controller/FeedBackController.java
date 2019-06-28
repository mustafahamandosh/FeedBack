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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    private FeedbackSender feedbackSender;

    private List<FieldError> errors = null;

    private String message = "";

    public FeedBackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }

    @PostMapping
    public String sendFeedback(@Valid @RequestBody FeedBack feedBack,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errors = bindingResult.getFieldErrors();
            for (FieldError e : errors) {
                message = e.getDefaultMessage();
            }
            throw new ValidationException(message);
        }

//        this.feedbackSender.sendFeedback(
//                feedBack.getEmail(),
//                feedBack.getName(),
//                feedBack.getSubject(),
//                feedBack.getFeedback());

        return this.feedBackService.saveFeedBack(feedBack);
    }

    @GetMapping("/all")
    public List<FeedBack> getAllFeedback() {
        return this.feedBackService.listFeedBacks();
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id) {
        return this.feedBackService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedBack> findById(@PathVariable Long id) {
        return this.feedBackService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedBack> updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedBack feedback,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errors = bindingResult.getFieldErrors();
            for (FieldError e : errors) {
                message = e.getDefaultMessage();
            }
            throw new ValidationException(message);
        }
        return this.feedBackService.updateFeedback(id, feedback);
    }

    @GetMapping
    public List<FeedBack> findByNameOrEmail(@Valid @RequestBody FeedBack feedBack, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errors = bindingResult.getFieldErrors();
            for (FieldError e : errors) {
                message = e.getDefaultMessage();
            }
            throw new ValidationException(message);
        }
        return feedBackService.findByNameOrEmail(feedBack.getName(), feedBack.getEmail());
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
}
