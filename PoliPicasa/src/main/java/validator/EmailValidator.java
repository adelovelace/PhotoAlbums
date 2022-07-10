package validator;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Properties;

public class EmailValidator {
    private String code;

    public EmailValidator(String receiver){
        code = generateCode();
        //sendMessage(receiver);
    }

    private boolean sendMessage(String receiver) {
        String user = "polipicasa@sandbox6e02f8bd03e94e9f80af422931060331.mailgun.org\t";
        String password = "7054f530ef645c12dc0b2b8f2234bbfe-1b8ced53-e8dc2abc";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.mailgun.org");  //Google SMTP server
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(receiver));
            message.setSubject("CODIGO DE VERIFICACION: POLIPICASA");
            message.setText(generateMessage());
            Transport.send(message);
            System.out.println("exito");
            return true;
        }
        catch (MessagingException me) {
            System.out.println("error");
            me.printStackTrace();   //Si se produce un error
        }
        return false;
    }

    private String generateCode(){
        String lowerletter = "abcdefghijklmnopqrstuvwxyz";
        String upperletter = lowerletter.toUpperCase();
        String number = "0123456789";
        String data = lowerletter+upperletter+number;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int charIndex = random.nextInt(data.length());
            char randomChar = data.charAt(charIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public String getCode(){
        return this.code;
    }

    private String generateMessage(){
        String message = "Bienvenido a Polipicasa: Tu aplicacion para guardar fotos\nTu codigo de validacion es:"+code+"" +
                "\nIngresa este codigo para registrarte en la aplicacion";
        return message;
    }
}
