package com.myprojectexample.myprojectdemo.controller;

import com.myprojectexample.myprojectdemo.model.OfferLetter;
import com.myprojectexample.myprojectdemo.repository.OfferLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OfferLetterController {

    @Autowired
    private OfferLetterRepository offerLetterRepository;

    @PostMapping("/uploadOfferLetter")
    public ResponseEntity<String> uploadOfferLetters(@RequestParam("offerLetter") MultipartFile offerLetter) {
        try {
            OfferLetter document = new OfferLetter();
            document.setOfferLetter(offerLetter.getBytes());
            offerLetterRepository.save(document);
            return ResponseEntity.ok("Offer Letter uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload offer letter");
        }
    }

    @GetMapping("/downloadOfferLetter/{id}")
    public ResponseEntity<byte[]> downloadOfferLetter(@PathVariable Long id) {
        Optional<OfferLetter> result = offerLetterRepository.findById(id);
        if (result.isPresent()) {
            OfferLetter document = result.get();
            byte[] offerLetterBytes = document.getOfferLetter();
            return ResponseEntity.ok().header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "attachment; filename=\"OfferLetter.pdf\"")
                    .body(offerLetterBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
