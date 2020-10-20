package com.example.blog.core;

public enum TitleLanguage {
    ENG(""),
    RU("C:\\Users\\user\\Desktop\\blog\\blog\\src\\main\\resources\\titles.properties");

    private String link;

    TitleLanguage(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
