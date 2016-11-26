package com.proximate.www.dashmate.utils;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Component;

import com.sun.mail.smtp.SMTPMessage;


@Component
public class EnviaMail {

    private final static String protocol = "smtp";
	private final static String from     = "servicios@proximateapps.com";
	private final static String host     = "email-smtp.us-east-1.amazonaws.com";
	private final static String password = "ArPec8p374QUSfHo80sAS4m5ShAgo/iOYlNQOv6/XTn+";
	private final static String port     = "465";
	private final static String username = "AKIAJK7XNV45BJ4YX7WQ";

    private Properties properties = System.getProperties();
    
    private Session session = null;
    private File archivo    = null;
    
    public boolean enviar(String to, String asunto, String mensaje) {

        properties.put("mail.transport.protocol",      protocol);
        properties.put("mail.smtp.host",               host);
        properties.put("mail.smtp.port",               port);
        properties.put("mail.mime.charset",            "UTF-8");
        properties.put("mail.user",                    username);
        properties.put("mail.password",                password);
        properties.put("mail.smtp.auth",              "true");
        properties.put("mail.smtp.starttls.enable",   "true");
        properties.put("mail.smtp.starttls.required", "true");
        
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        
        Authenticator authenticator = new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(username, password);
        	}
		};
        
        session = Session.getInstance(properties,authenticator);
		
        try {
        	
        	Transport transport = session.getTransport();
        	
        	transport.connect(host, username, password);
        	
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(asunto, "UTF-8");
            message.setText(mensaje, "UTF-8","html");
            transport.sendMessage(message, message.getAllRecipients());
        } catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e) {
        	e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
        	e.printStackTrace();
            return false;
        }
        return true;
    }

    
	public boolean sendMail(String to, String asunto, String mensaje, String logoHead, String logoBottom) throws Exception {

        properties.put("mail.transport.protocol",      protocol);
        properties.put("mail.smtp.host",               host);
        properties.put("mail.smtp.port",               port);
        properties.put("mail.mime.charset",            "UTF-8");
        properties.put("mail.user",                    username);
        properties.put("mail.password",                password);
        properties.put("mail.smtp.auth",              "true");
        properties.put("mail.smtp.starttls.enable",   "true");
        properties.put("mail.smtp.starttls.required", "true");
        
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        
        Authenticator authenticator = new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(username, password);
        	}
		};
        
        session = Session.getInstance(properties,authenticator);

		// Create a default MimeMessage object.

		SMTPMessage message = new SMTPMessage(session);
		MimeMultipart multiparte = new MimeMultipart("related");

		MimeBodyPart texto = new MimeBodyPart();
		texto.setText(mensaje.toString(), "US-ASCII", "html");
		multiparte.addBodyPart(texto);
		
		MimeBodyPart imagePart = new MimeBodyPart();
		
		imagePart.attachFile(new File(logoHead));
		imagePart.setContentID("<logo_head>");
		imagePart.setDisposition(MimeBodyPart.INLINE);
		multiparte.addBodyPart(imagePart);

		MimeBodyPart imagePart2 = new MimeBodyPart();
		imagePart2.attachFile(new File(logoBottom));
		imagePart2.setContentID("<logo_bottom>");
		imagePart2.setDisposition(MimeBodyPart.INLINE);
		multiparte.addBodyPart(imagePart2);

		message.setContent(multiparte);
		message.setSubject(MimeUtility.encodeText(asunto, "utf-8", "B"));
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(from));

		if (archivo != null) {
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setFileName(archivo.getName());
			adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
			multiparte.addBodyPart(adjunto);
		}

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		// Send message
		// Transport.send(message);

		Transport t = session.getTransport("smtp");
		t.connect(username, password);
		t.sendMessage(message, message.getAllRecipients());
		t.close();

		return true;
	}
    
	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

}