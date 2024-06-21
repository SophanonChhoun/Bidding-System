package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private Configuration freemarkerConfig;

    @Override
    public void sendMail(String email, String itemName) {

        log.info("=============Start send email=============");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            Template t = freemarkerConfig.getTemplate("congratulation.ftl");
            HashMap<String, Object> model = new HashMap<>();
            model.put("itemName", itemName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject("Congratulation");
        }catch (Exception exception) {
            log.error("Error send email: " + exception.getMessage());
        }
        log.info("=============Finished send email=============");
    }
}
