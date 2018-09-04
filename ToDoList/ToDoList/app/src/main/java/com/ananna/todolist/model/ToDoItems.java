package com.ananna.todolist.model;

/**
 * Created by Ananna on 30-Dec-17.
 */

public class ToDoItems {
    private int id;
    private int toDoid;
    private String toDo;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToDoid() {
        return toDoid;
    }

    public void setToDoid(int toDoid) {
        this.toDoid = toDoid;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}

