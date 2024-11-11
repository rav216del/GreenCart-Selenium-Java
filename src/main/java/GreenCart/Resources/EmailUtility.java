package GreenCart.Resources;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtility {

	private final String fromEmail = "raviagg.del@gmail.com"; // Your email
	private final String toEmail = "rav216del@gmail.com"; // Manager's email
	private final String smtpHost = "smtp.gmail.com"; // SMTP host
	private final String smtpPort = "587"; // SMTP port (usually 587 for Gmail)

	// Send email with report attached
	public void sendReport(String[] reportPaths) throws MessagingException, IOException {
		// Set email properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Create the session
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("raviagg.del@gmail.com", "Erica@12345");
			}
		});

		// Compose the message
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmail));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject("Test Execution Report");

		// Create the message body
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setText("Hello,\n\nPlease find the attached test execution report.\n\nBest Regards,\nYour Team");

//		// Create the attachment
//		MimeBodyPart attachmentPart = new MimeBodyPart();
//		attachmentPart.attachFile(reportPath); // Add the path to the test report here
//
//		// Create multipart to combine text and attachment
//		Multipart multipart = new MimeMultipart();
//		multipart.addBodyPart(textPart);
//		multipart.addBodyPart(attachmentPart);

		// Create multipart to combine text and attachments
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(textPart);

		// Attach each report file
		for (String reportPath : reportPaths) {
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(reportPath); // Attach the report
			multipart.addBodyPart(attachmentPart);
		}
		// Set the content of the email
		message.setContent(multipart);

		// Send the email
		Transport.send(message);
		System.out.println("Email sent successfully with the report.");
	}
}
