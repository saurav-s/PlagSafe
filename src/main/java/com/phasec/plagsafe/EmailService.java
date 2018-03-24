package com.phasec.plagsafe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("#{'${list.of.admin.email}'.split(',')}")
	private List<String> mailingList;

	/**
	 * 
	 * @param toEmail
	 * @param subject
	 * @param message
	 */
	public void sendMail(String toEmail, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
	}

	/**
	 * Send Alert message to all the admin emails
	 * @param message
	 */
	public void sendAlertMailToAdmins(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setCc(mailingList.toArray(new String[0]));
		mailMessage.setSubject("Team 109 - PlagSafe Application Alert");
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);

	}

}
