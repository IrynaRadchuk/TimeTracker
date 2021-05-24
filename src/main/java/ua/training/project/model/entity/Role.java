package ua.training.project.model.entity;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Role {
    GUEST(0),USER(1),ADMIN(2);
    private int id;

    Role(int id) {
        this.id = id;
    }

    public static Role getRole(User user) {
        return user.getRole();
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getRoleId() {
        return this.id;
    }

    public static Role getRoleById(int roleId){
        return Arrays.stream(Role.values()).filter(x->x.getRoleId()==roleId).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
