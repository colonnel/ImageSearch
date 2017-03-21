package com.gmail.berezin.serg.imagesearch.models;


public class SearchImage {
    private String url;
    private String name;
    private boolean isSelected;

    public SearchImage(String url, String name, boolean isSelected) {
        this.url = url;
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
