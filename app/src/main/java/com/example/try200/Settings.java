package com.example.try200;

public class Settings {
    private String button1Name;
    private String button2Name;
    private String button3Name;
    private int maxEventCount;

    public Settings(String button1Name, String button2Name, String button3Name, int maxEventCount) {
        this.button1Name = button1Name;
        this.button2Name = button2Name;
        this.button3Name = button3Name;
        this.maxEventCount = maxEventCount;
    }

    public String getButton1Name() {
        return button1Name;
    }

    public String getButton2Name() {
        return button2Name;
    }

    public String getButton3Name() {
        return button3Name;
    }

    public int getMaxEventCount() {
        return maxEventCount;
    }
}
