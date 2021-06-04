package ua.training.project.model.dao;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityDao)) return false;
        ActivityDao that = (ActivityDao) o;
        return id == that.id &&
                categoryId == that.categoryId &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, category);
    }

    @Override
    public String toString() {
        return "ActivityDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}
