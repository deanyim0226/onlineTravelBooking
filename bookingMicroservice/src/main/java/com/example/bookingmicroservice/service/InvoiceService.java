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


    public byte[] generateInvoice(Booking booking){

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try{
            PdfWriter.getInstance(document,outputStream);
            document.open();;
            addContent(document,booking);
            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    private void addContent(Document document, Booking booking) throws DocumentException{

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

        document.add(table);

        document.add(new Paragraph("\n"));


    }
}
