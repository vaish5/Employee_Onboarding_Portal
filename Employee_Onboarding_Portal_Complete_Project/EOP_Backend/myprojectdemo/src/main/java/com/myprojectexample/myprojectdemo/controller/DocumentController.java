package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.Document;
import com.myprojectexample.myprojectdemo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {
    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocuments(@RequestParam("photo") MultipartFile photo,
                                                  @RequestParam("degreeCertificate") MultipartFile degreeCertificate,
                                                  @RequestParam("aadhaarCard") MultipartFile aadhaarCard,
                                                  @RequestParam("panCard") MultipartFile panCard) {
        try {
            Document document = new Document();
            document.setPhoto(photo.getBytes());
            document.setDegreeCertificate(degreeCertificate.getBytes());
            document.setAadhaarCard(aadhaarCard.getBytes());
            document.setPanCard(panCard.getBytes());
            documentRepository.save(document);
            return ResponseEntity.ok("Documents uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload documents");
        }
    }
}
