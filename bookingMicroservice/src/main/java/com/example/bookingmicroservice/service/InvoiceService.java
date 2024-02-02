package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Booking;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class InvoiceService {


    public byte[] generateInvoice(Booking booking, String hotelName){

        Document document = new Document(); // create new pdf document
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // to store the content of the PDF document in memory.

        try{
            PdfWriter.getInstance(document,outputStream); // pdfwriter is responsible for writing the content to the PDF document.
            document.open();;
            addContent(document,booking, hotelName);
            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    private void addContent(Document document, Booking booking, String hotelName) throws DocumentException{

        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
        Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        Paragraph header = new Paragraph("Invoice", headerFont);
        header.setAlignment(Element.ALIGN_CENTER);

        document.add(header);
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1,2});

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Invoice Number", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(booking.getBookingId()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Invoice Date", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(LocalDate.now().toString(),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer Name", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(booking.getUserName(),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Customer Mobile", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(booking.getCustomerMobile(),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Hotel", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(hotelName,regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Check-In Date", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(booking.getCheckInDate()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Check-out Date", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(booking.getCheckOutDate()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("No. of Rooms", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(booking.getNoRooms()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price per Room", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("$" + String.valueOf(booking.getPrice()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Discount", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("$" + String.valueOf(booking.getDiscount()),regularFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tax rate", regularFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(booking.getTaxRateInPercent() *100) + "%",regularFont));
        table.addCell(cell);

        document.add(table);

        document.add(new Paragraph("\n"));

        Paragraph totalSavings = new Paragraph("Total Amount: $" + booking.getFinalCharges(),headerFont);
        totalSavings.setAlignment(Element.ALIGN_RIGHT);

        document.add(totalSavings);


    }
}
