package com.example.textbookmarketplace;

public abstract class MarketplaceItem {
    private String title;

    public MarketplaceItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Abstract method that all items must use
    public abstract void displayItemDetails();
}