package com.example.textbookmarketplace;

public class Textbook {
    private int id;
    private String title;
    private String seller;
    private double price;
    private String category;
    private int copies;
    private String bankingInfo;

    // Constructor used when reading from the database
    public Textbook(int id, String title, String seller, double price, String category, int copies, String bankingInfo) {
        this.id = id;
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.category = category;
        this.copies = copies;
        this.bankingInfo = bankingInfo;
    }

    // Constructor used when adding a new book
    public Textbook(String title, String seller, double price, String category, int copies, String bankingInfo) {
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.category = category;
        this.copies = copies;
        this.bankingInfo = bankingInfo;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSeller() { return seller; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getCopies() { return copies; }
    public String getBankingInfo() { return bankingInfo; }
}