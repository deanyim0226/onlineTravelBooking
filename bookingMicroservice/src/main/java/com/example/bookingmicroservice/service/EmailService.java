package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.domain.Guest;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private InvoiceService invoiceService;

    @Async
    public void sendBookingConfirmation(Booking booking){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message);

            byte[] invoiceBytes = invoiceService.generateInvoice(booking);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setContent(invoiceBytes, "application/pdf");
            attachmentPart.setFileName("invoice.pdf");

            MimeBodyPart textPart = new MimeBodyPart();

            textPart.setContent(this.getEmailBody(booking), "text/html;charset=UTF-8");
            //set content type to HTML

            helper.setTo(booking.getUserEmail());
            helper.setSubject("Time to Unwind: Your Hotel Stay is Official!");
            helper.setText(this.getEmailBody(booking),true);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);
            multipart.addBodyPart(textPart);

            message.setContent(multipart);
            javaMailSender.send(message);

        }catch (Exception e){

        }
    }

    public String getEmailBody(Booking booking){

        StringBuilder guestTable = new StringBuilder();

        for(Guest guest : booking.getGuests()){
            String guestRow = """
                <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                </tr>
                """.formatted(guest.getFirstName(),
                    guest.getLastName(),
                    guest.getGender(),
                    guest.getAge());
        
            guestTable.append(guestRow);
        }



        return guestTable.toString();

    }


}
