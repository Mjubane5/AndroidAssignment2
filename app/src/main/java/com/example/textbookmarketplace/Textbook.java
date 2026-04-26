package com.example.textbookmarketplace;

public class Textbook extends MarketplaceItem implements Sellable {

    private String sellerName;
    private double price;
    private int stockCount;
    private String bankingInfo;

    // Upgraded Constructor: Now it asks for all the Assignment 2 requirements
    public Textbook(String sellerName, String title, int stockCount, double price, String bankingInfo) {
        super(title);
        this.sellerName = sellerName;
        this.stockCount = stockCount;
        this.price = price;
        this.bankingInfo = bankingInfo;
    }

    // New Getters so the Search screen can find books by Seller Name
    public String getSellerName() {
        return sellerName;
    }

    public String getBankingInfo() {
        return bankingInfo;
    }

    @Override
    public void displayItemDetails() {
        System.out.println("Seller: " + sellerName + " | Textbook: " + getTitle() + " | Price: R" + price + " | Copies: " + stockCount);
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getStockCount() {
        return this.stockCount;
    }

    @Override
    public void decreaseStock(int amount) {
        if (this.stockCount >= amount) {
            this.stockCount -= amount;
        }
    }
}