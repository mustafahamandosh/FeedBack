package feedback.demo.mail;

public interface FeedbackSender {
    void sendFeedback(String from, String name, String subject, String feedback);
}
