package com.scheduling.serviceImplimentation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.scheduling.dto.BookingDto;
import com.scheduling.services.EmailService;

@Service
public class EmailSender implements EmailService {

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//     @Async
//     private void sendEmail(SimpleMailMessage email) {
//       try {
//             javaMailSender.send(email);
//       } catch (Exception e) {
//           e.printStackTrace();
//           throw new RuntimeException("Email could not be send");
//       }
//       
//     }

    @Override
    public void sendmail(String userEmail, String otpValue) {
        String from = "edost_community@yahoo.com";
        String pass ="oXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
       // Recipient's email ID needs to be mentioned.
      String to = userEmail;
      String host = "smtp.mail.yahoo.com";

      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.user", from);
      properties.put("mail.smtp.password", pass);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.auth", "true");

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("OTP for Verification");

         // Now set the actual message
         message.setText("Your Otp is: "+otpValue);

         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, pass);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
        
        
//        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(userEmail);
//            mailMessage.setSubject("Email verification");
//            mailMessage.setFrom("blankteam9933@gmail.com");
//            mailMessage.setText("Otp for verification:=>" + otpValue);
//            sendEmail(mailMessage);
            
            
            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        
    }

    @Override
    public void sendBookingMail(String userEmail, BookingDto converter) {
        String from = "edost_community@yahoo.com";
        String pass ="olugwfzdfjzabsdw";
       // Recipient's email ID needs to be mentioned.
      String to = userEmail;
      String host = "smtp.mail.yahoo.com";

      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.user", from);
      properties.put("mail.smtp.password", pass);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.auth", "true");

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Booking Conformation");

         // Now set the actual message
         message.setText("  Your Slot:Start time:"+ converter.getSlotDetailsDto().getStartTime()
                              +"  End time: "+  converter.getSlotDetailsDto().getEndTime()
                              +"  Slot Date :"+ converter.getBookingDate()
                              +"  Expert EmailId :"+ converter.getExpertDto().getExpertEmail()
                              +"  Amount Paid :"+ converter.getAmount()
                 );

         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, pass);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
    }
    
    public void sendReportEmail(String userEmail, String string) {
        String from = "edost_community@yahoo.com";
        String pass ="olugwfzdfjzabsdw";
       // Recipient's email ID needs to be mentioned.
      String to = userEmail;
      String host = "smtp.mail.yahoo.com";

      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.user", from);
      properties.put("mail.smtp.password", pass);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.auth", "true");

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Report");

         // Now set the actual message
         message.setText("your "+ string +" have a report ");

         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(host, from, pass);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
    
}
}
