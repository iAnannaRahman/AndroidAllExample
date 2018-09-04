package com.ananna.firebaseconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Publisher {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name  +'\n';
    }

    public static String publishers(List<Publisher> authorList){
        String items = "";

        for (Publisher item: authorList) {
            items += item.getName() + "\n";
        }

        return items;
    }
}
