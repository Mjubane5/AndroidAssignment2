package com.example.textbookmarketplace;

public interface Sellable {
    double getPrice();
    int getStockCount();
    void decreaseStock(int amount);
}