package com.kusithm.hdmedi_server.infra.smtp;

import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.UNAUTHORIZED_GOOGLE_SMTP;

@Slf4j
@RequiredArgsConstructor
@Component
public class GoogleEmailProvider {
    private static final String SERVER_MAIL = "rhks33rhs@gmail.com";
    private static final String EMAIL_TITLE = "HDmedi";
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public void sendEmail(String toEmail, String content) {
        MimeMessage emailForm = createEmailForm(toEmail, content);
        javaMailSender.send(emailForm);
    }

    private MimeMessage createEmailForm(String email, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.addRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject(EMAIL_TITLE);
            message.setFrom(SERVER_MAIL);
            message.setText(setContext(content), "utf-8", "html");
        } catch (MessagingException e) {
            log.error("Failed to send Email", e);
            throw new UnauthorizedException(UNAUTHORIZED_GOOGLE_SMTP);
        }
        return message;
    }

    private String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process("mail", context);
    }
}
