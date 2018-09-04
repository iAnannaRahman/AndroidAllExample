package com.ananna.todolist.model;

import java.util.List;

/**
 * Created by Ananna on 30-Dec-17.
 */

public class ToDo {
    private String title;
    private int id;
    private List<ToDoItems> items;

    public List<ToDoItems> getItems() {
        return items;
    }

    public void setItems(List<ToDoItems> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

