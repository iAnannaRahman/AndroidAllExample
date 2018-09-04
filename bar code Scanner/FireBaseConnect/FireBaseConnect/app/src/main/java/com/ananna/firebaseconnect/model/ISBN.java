package com.ananna.firebaseconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ISBN {

    @SerializedName("publishers")
    @Expose
    private List<Publisher> publishers = null;
    @SerializedName("pagination")
    @Expose
    private String pagination;
    @SerializedName("identifiers")
    @Expose
    private Identifiers identifiers;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("number_of_pages")
    @Expose
    private Integer numberOfPages;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;
    @SerializedName("subject_people")
    @Expose
    private List<SubjectPerson> subjectPeople = null;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("authors")
    @Expose
    private List<Author> authors = null;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("by_statement")
    @Expose
    private String byStatement;
    @SerializedName("excerpts")
    @Expose
    private List<Excerpt> excerpts = null;
    @SerializedName("publish_places")
    @Expose
    private List<PublishPlace> publishPlaces = null;


    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public Identifiers getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<SubjectPerson> getSubjectPeople() {
        return subjectPeople;
    }

    public void setSubjectPeople(List<SubjectPerson> subjectPeople) {
        this.subjectPeople = subjectPeople;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getByStatement() {
        return byStatement;
    }

    public void setByStatement(String byStatement) {
        this.byStatement = byStatement;
    }

    public List<Excerpt> getExcerpts() {
        return excerpts;
    }

    public void setExcerpts(List<Excerpt> excerpts) {
        this.excerpts = excerpts;
    }

    public List<PublishPlace> getPublishPlaces() {
        return publishPlaces;
    }

    public void setPublishPlaces(List<PublishPlace> publishPlaces) {
        this.publishPlaces = publishPlaces;
    }



}
