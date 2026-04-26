package com.example.textbookmarketplace;

public class Textbook extends MarketplaceItem implements Sellable {

    private double price;
    private int stockCount = 1; // Assuming 1 copy being sold by default

    // The Constructor: This is called when you hit the "Post" button
    public Textbook(String title, double price) {
        super(title); // This now works because MarketplaceItem exists!
        this.price = price;
    }

    // Fulfilling the rule from the MarketplaceItem abstract class
    @Override
    public void displayItemDetails() {
        System.out.println("Textbook: " + getTitle() + " | Price: R" + price);
    }

    // Fulfilling the first rule from Abahle's Sellable interface
    @Override
    public double getPrice() {
        return this.price;
    }

    // Fulfilling the second rule from Abahle's Sellable interface
    @Override
    public int getStockCount() {
        return this.stockCount;
    }

    // Fulfilling the final missing rule from Abahle's Sellable interface
    @Override
    public void decreaseStock(int amount) {
        if (this.stockCount >= amount) {
            this.stockCount -= amount;
        }
    }
}