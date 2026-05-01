package com.example.textbookmarketplace;

public class Textbook {
    private int id;
    private String title;
    private String sellerName;
    private double price;
    private int stockCount;
    private String bankingInfo;

    // Constructor with 6 parameters (ID + 5 details)
    public Textbook(int id, String title, String sellerName, double price, int stockCount, String bankingInfo) {
        this.id = id;
        this.title = title;
        this.sellerName = sellerName;
        this.price = price;
        this.stockCount = stockCount;
        this.bankingInfo = bankingInfo;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSellerName() { return sellerName; }
    public double getPrice() { return price; }
    public int getStockCount() { return stockCount; }
    public String getBankingInfo() { return bankingInfo; }
}