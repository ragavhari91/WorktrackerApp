package com.app.worktrackerapp.model;

/**
 * Created by ragavendran on 27-Oct-2015.
 */
public class Status
{
    public int id;
    public String name;
    public String description;

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

}
