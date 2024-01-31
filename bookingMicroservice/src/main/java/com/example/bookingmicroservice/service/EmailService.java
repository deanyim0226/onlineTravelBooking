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
            System.out.println("error occurs " + e.getMessage() );
            e.printStackTrace();
        }
    }

    public String getEmailBody(Booking booking){
        System.out.println("get email body is called");
        StringBuilder guestTable = new StringBuilder();

        for(Guest guest : booking.getGuests()){
            String guestRow = """
                <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                </tr>
                """.formatted(
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getGender(),
                        guest.getAge()
            );

            guestTable.append(guestRow);
        }


        String htmlTemplate = """
                <!DOCTYPE html>
                <html>
                    <head>Hotel Confirmation</head>
                    <body>
                        <h1> Your Stay at %s is confirmed! </h1>
                        <p> Check-in Date : %s </p>
                        <p> Check-out Date : %s </p>
                        <p> Booked on : %s </p>
                        <p> Status : %s </p>
                        
                        <h2> Guest Information </h2>
                        <table>
                          
                            <tr>
                                <th>Guest Details</th>
                            </tr>
                            <tr>
                                <th> First Name </th>
                                <th> Last Name </th>
                                <th> Gender </th>
                                <th> Age </th>
                            </tr>
                         
                            <tr>
                                 <td>%s</td>
                            </tr>

                        </table>
                        <hr/>
                        <table>
                            <tr>
                                <th> Booking Details </th>
                            </tr>
                            <tr>
                                <td>
                                    <p> Room Type: %s </p>
                                    <p> No. of Rooms: %s </p>
                                    <p> Price per Room: $%.2f </p>
                                    <p> Discount: $%.2f </p>
                                    <p> Tax Rate: %.2f%% </p>
                                    <p> Final Charges: $%.2f </p>
                   
                                </td>
                            </tr>
                        </table>
                    </body>
                </html>
             """.formatted(
                     booking.getHotelName(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getBookedOnDate(),
                booking.getStatus(),
                guestTable.toString(),
                booking.getRoomType(),
                booking.getNoRooms(),
                booking.getPrice(),
                booking.getDiscount(),
                booking.getTaxRateInPercent() * 100,
                booking.getFinalCharges()
        );

        return htmlTemplate;

    }


}
