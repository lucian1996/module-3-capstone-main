package com.techelevator.model;

public class Item {
    private int itemId;
    private int listId;
    //TODO: Can be null.
    private int claimedId;
    private String dateModified;
    //TODO: lastModifier defaults to creator of the list.
    private int lastModifier;
    private int quantity;
    private String description;


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getClaimedId() {
        return claimedId;
    }

    public void setClaimedId(int claimedId) {
        this.claimedId = claimedId;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public int getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(int lastModifier) {
        this.lastModifier = lastModifier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", listId=" + listId +
                ", claimedId=" + claimedId +
                ", dateModified='" + dateModified + '\'' +
                ", lastModifier=" + lastModifier +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
