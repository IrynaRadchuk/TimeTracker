package ua.training.project.model.dao;

import ua.training.project.controller.command.Command;

/**
 * DAO class to manage activities in database
 *
 * @author Iryna Radchuk
 */
public class ActivityDao {
    private int id;
    private String name;
    private int categoryId;
    private String category;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
