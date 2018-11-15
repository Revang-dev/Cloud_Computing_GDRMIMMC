package mail;

import javax.mail.MessagingException;

public class MailSender {
	
	public static boolean SendLinkTo(String mailUser,String dlUrl) {
		try {
			GoogleMail.Send("cloudcomputingsophiaproject", "Password06*", mailUser, "Lien de téléchargement", dlUrl);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
