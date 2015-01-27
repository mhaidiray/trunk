package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohamedsqualli
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SMTPManager {
    
    public static String mailUtil="noreply.carpooling.sopra@gmail.com";
    public static String passUtil="c@rpoolings0pra";
    
        /** Envoi d'une confirmation lors de la suppression d'un compte à l'adresse "address" */
        public static void sendDeleteConfirmation(String address){
            SMTPManager.sendMail(address,"Bonjour,\n"+ "Votre compte sur l'application Sopra Covoiturage a été supprimé avec succès.\n\n\n"
                    +"Pour toute réclamation, veuillez contactez l'administrateur à l'adresse mail : XXXXXX"
                    ,"Suppression de votre compte sur Sopra Covoiturage");
        }
        
        /** Envoi d'une notification à l'adresse "address" lors d'un nouveau trajet disponible de la personne "newPrenom newNom" */
        public static void sendNotification(String address,String newPrenom,String newNom){
            SMTPManager.sendMail(address,"Bonjour,\n"+ "Une nouvelle personne : '"+newPrenom+" "+newNom+"' propose un trajet qui pourrait vous intérésser.\n\n\n"
                    +"Pour annuler les notifications par mail, veuillez vous rendre sur votre espace client.\n"
                    +"Pour toute réclamation, veuillez contactez l'administrateur à l'adresse mail : XXXXXX"
                    ,"Nouveau trajet disponible sur Sopra Covoiturage");
        }
        
        /** Envoi d'un nouveau mot de passe newPasswd à l'adresse mail "address" */
        public static void sendNewPassword(String address, String newPasswd){
            SMTPManager.sendMail(address,"Bonjour,\n"+ "Votre mot de passe a été réinitialisé avec succès.\n\n"
                    +"Votre nouveau mot de passe est : "+newPasswd+"\n\n\n"
                    +"Pour toute réclamation, veuillez contactez l'administrateur à l'adresse mail : XXXXXX"
                    ,"Réinitialisation de votre mot de passe sur Sopra Covoiturage");
        }
        
        /** Envoi d'une confirmation lors de la création d'un compte à l'adresse "address" avec le mot de passe "mdp" */
        public static void sendCreateConfirmation (String adresse, String mdp){
            String mess= "Bonjour,\n"+"Votre compte sur l'application Sopra Covoiturage a été crée avec succès.\n\n\n"+"Nom d'utilisateur: "+ adresse + "\n"+"Mot de passe: "+mdp+"\n\n\n"
                    +"Pour toute réclamation, veuillez contacter l'administrateur à l'adresse mail : XXXX";
            String subject="Création de votre compte sur Sopra Covoiturage" ;
            SMTPManager.sendMail(adresse,mess,subject);   
        }

        /** Fonction générique pour envoyez un mail à l'adresse "address" dont le contenu est "msg" et le sujet "subject" */
        public static void sendMail(String address,String msg,String subject){
            
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailUtil,passUtil);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.carpooling.sopra@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(address));
			message.setSubject(subject);
			message.setText(msg);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        /** Fonction utile uniquement pour les tests, n'est absolument pas utilisée lors du déroulement de l'application */
	public static void main(String[] args) {
            //SMTPManager.sendMail("squallih@etud.insa-toulouse.fr", "Voici mon message,"
            //        + "\n Hehe cocoo !", "Ca marche !");
            SMTPManager.sendCreateConfirmation("mohamed_squalli@hotmail.com","coco");
            SMTPManager.sendDeleteConfirmation("mohamed_squalli@hotmail.com");
            SMTPManager.sendNewPassword("mohamed_squalli@hotmail.com", "coco");
	}
}