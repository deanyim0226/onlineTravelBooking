package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Booking;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    public byte[] generateInvoice(Booking booking){
        Document document = new Document();


        return null;
    }

    private void addContent(Document document, Booking booking) throws DocumentException{


    }
}
