package feedback.demo.controller;

import feedback.demo.model.FeedBack;
import feedback.demo.mail.FeedbackSender;
import feedback.demo.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
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
    public void sendFeedback(@RequestBody @Valid  FeedBack feedBack,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback has errors; Can not send feedback;");
        }

        this.feedbackSender.sendFeedback(
                feedBack.getEmail(),
                feedBack.getName(),
                feedBack.getSubject(),
                feedBack.getFeedback());

        this.feedBackService.saveFeedBack(feedBack);
    }

    @GetMapping
    public List<FeedBack> getAllFeedback(){
        return this.feedBackService.listFeedBacks();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.feedBackService.deleteById(id);
    }

    @GetMapping("/{id}")
    public FeedBack findById(@PathVariable Long id){
        return this.feedBackService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateFeedback(@RequestBody FeedBack feedBack, @PathVariable Long id){
        this.feedBackService.updateFeedback(feedBack);
    }

    @GetMapping("/{name}/{email}")
    public List<FeedBack> findByNameOrEmail(@PathVariable  String name, @PathVariable String email) {
        return  feedBackService.findByNameOrEmail(name, email);
    }
}
