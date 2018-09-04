package com.ananna.firebaseconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Excerpt {

    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("first_sentence")
    @Expose
    private Boolean firstSentence;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFirstSentence() {
        return firstSentence;
    }

    public void setFirstSentence(Boolean firstSentence) {
        this.firstSentence = firstSentence;
    }

}
