package com.example.sqlitelec10;

public class CustomerModels {
    private String name;
    private int age;
    private boolean isActive;
    private int id;

    public CustomerModels(String name, int age, boolean isActive, int id) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CustomerModels{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", id=" + id +
                '}';
    }
}
