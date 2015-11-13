package com.app.worktrackerapp.model;

/**
 * Created by ragavendran on 27-Oct-2015.
 */
public class Role
{
    private int id;
    private String name;
    private String description;
    private Status status_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Status status_code) {
        this.status_code = status_code;
    }
}
