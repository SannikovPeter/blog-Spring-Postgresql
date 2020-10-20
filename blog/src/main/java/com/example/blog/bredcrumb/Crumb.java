package com.example.blog.bredcrumb;

public class Crumb {

    private String name;
    private String address;
    private boolean isLast = false;

    public Crumb(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
