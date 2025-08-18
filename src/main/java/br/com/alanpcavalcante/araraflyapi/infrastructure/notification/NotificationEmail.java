package br.com.alanpcavalcante.araraflyapi.infrastructure.notification;

import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.Notification;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.TextField;
import br.com.alanpcavalcante.araraflyapi.domain.user.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



@Component
public class NotificationEmail implements Notification {

    private final JavaMailSender javaMailSender;

    public NotificationEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Override
    public void send(Email to, TextField subject, TextField htmlContent) {
        System.out.println("Thread: " + Thread.currentThread().getName());

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to.getEmail());
            helper.setSubject(subject.getValue());
            helper.setText(htmlContent.getValue(), true);

            javaMailSender.send(message);
            System.out.printf("E-mail HTML enviado com sucesso para %s%n", to);
        } catch (Exception e) {
            System.out.printf("Erro ao enviar e-mail para %s: %s%n", to, e.getMessage());
        }
    }
}