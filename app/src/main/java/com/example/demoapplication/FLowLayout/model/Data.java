package com.example.demoapplication.FLowLayout.model;

import android.text.TextUtils;

public class Data {
    private String label;
    private String value;

    public Data(String label) {
        this.label = label;
        this.value = label;
    }

    public Data(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
