package com.example.smartnotes;

public class Note {
    private int id;
    private String title;
    private String content;
    private String date;
    private String category; // Work, Personal, Study

    // Constructors
    public Note() {
    }

    public Note(String title, String content, String date, String category) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    public Note(int id, String title, String content, String date, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
