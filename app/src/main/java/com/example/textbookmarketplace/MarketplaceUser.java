package com.example.textbookmarketplace;

public abstract class MarketplaceUser {
    protected String name;
    protected String email;

    public MarketplaceUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Abstract method: Every user type must implement how they interact with the app
    public abstract String getUserRole();

    public String getName() {
        return name;
    }
}