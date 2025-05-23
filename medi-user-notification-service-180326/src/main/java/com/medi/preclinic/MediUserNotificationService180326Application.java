package com.medi.preclinic;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.medi.preclinic.util.NotificationConstants;

@SpringBootApplication
public class MediUserNotificationService180326Application {

	/**
	 * spring3 introduced Environment, message resources will be loaded and autodetected the properties file
	 * 
	 */
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(MediUserNotificationService180326Application.class, args);
	}
	
	@Bean
	public JavaMailSender mailSender() throws IOException{
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties properties = mailSender.getJavaMailProperties();
		mailSender.setHost(this.environment.getProperty(NotificationConstants.EMAIL_HOST_NAME));
		mailSender.setPort(Integer.parseInt(this.environment.getProperty(NotificationConstants.EMAI_PORT)));
		properties.put("mail.smtp.starttls.enable", true);
		//mailSender.setProtocol(this.environment.getProperty(NotificationConstants.EAMI_PROTOCOL));
		mailSender.setUsername(this.environment.getProperty(NotificationConstants.EMAIL_USER_NAME));
		mailSender.setPassword(this.environment.getProperty(NotificationConstants.EMAIL_PASSWORD));
		
		return mailSender;
	}
	
	@Bean
	public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }
	private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        //templateResolver.setPrefix("/mail/");
        templateResolver.setPrefix("classpath:mail");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(NotificationConstants.EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
