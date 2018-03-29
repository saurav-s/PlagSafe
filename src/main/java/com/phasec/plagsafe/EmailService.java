package com.phasec.plagsafe;

/**
 * Provides a mechanism to send emails 
 * @author sanketsaurav
 *
 */
public interface EmailService {

	/**
	 * 
	 * @param toEmail
	 * @param subject
	 * @param message
	 */
	public void sendMail(String toEmail, String subject, String message);

	/**
	 * Send Alert message to all the admin emails
	 * @param message
	 */
	public void sendAlertMailToAdmins(String message);
}
