package com.medi.preclinic.resource;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.medi.preclinic.notification.bean.NotificationBean;
import com.medi.preclinic.util.EmailTemplates;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1.0")
public class NotificationResource {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	//@PostMapping("/notifications")
	@RequestMapping(value = "/notifications", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public NotificationBean sendEmailNotification(@RequestBody NotificationBean notificationBean) {
		/**
		 * to whoome message to be send
		 */
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(notificationBean.getFrom());
			helper.setTo(notificationBean.getTo());
			if(notificationBean.getBcc() != null && notificationBean.getBcc().length >0) {
				helper.setBcc(notificationBean.getBcc());
			}
			String emailTemplateName = EmailTemplates.getTemplateName(notificationBean.getEmailTemplateType());
			for(String to : notificationBean.getTo()) {
				//prepare the evaluation context
				final Context ctx = new Context(Locale.ENGLISH);
				ctx.setVariable("name", to);
				helper.setText("greeting", "hello");
				String callbackUrl = notificationBean.getCallbackUrl();
				String code = notificationBean.getUserVerificationCode();
				callbackUrl = callbackUrl+"/"+code;
				//create html body using thymeleaf
				final String htmlContent = this.templateEngine.process(emailTemplateName, ctx);
				helper.setText(htmlContent, true);
				//send mail
				this.mailSender.send(helper.getMimeMessage());
			}
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificationBean;
	}
}
