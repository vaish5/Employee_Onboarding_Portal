package com.myprojectexample.myprojectdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Lob
    @Column(name = "degree_certificate")
    private byte[] degreeCertificate;

    @Lob
    @Column(name = "aadhaar_card")
    private byte[] aadhaarCard;

    @Lob
    @Column(name = "pan_card")
    private byte[] panCard;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getDegreeCertificate() {
        return degreeCertificate;
    }

    public void setDegreeCertificate(byte[] degreeCertificate) {
        this.degreeCertificate = degreeCertificate;
    }

    public byte[] getAadhaarCard() {
        return aadhaarCard;
    }

    public void setAadhaarCard(byte[] aadhaarCard) {
        this.aadhaarCard = aadhaarCard;
    }

    public byte[] getPanCard() {
        return panCard;
    }

    public void setPanCard(byte[] panCard) {
        this.panCard = panCard;
    }
}
