package com.ananna.firebaseconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ananna on 24-Jan-18.
 */


public class Author {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;

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

    @Override
    public String toString() {
        return  name + '\n';
    }

    public static String authors(List<Author> authorList){
        String items = "";

        for (Author item: authorList) {
            items += item.getName() + "\n";
        }

        return items;
    }

}
