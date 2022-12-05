package com.techelevator.model;

public class List {
    private int listId;
    private int groupId;
    private String description;
    private String listName;


    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        return "List{" +
                "listId=" + listId +
                ", groupId=" + groupId +
                ", description='" + description + '\'' +
                ", listName='" + listName + '\'' +
                '}';
    }
}
