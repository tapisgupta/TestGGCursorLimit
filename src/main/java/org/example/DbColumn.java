package org.example;

public class DbColumn {
    private final String name;

    public DbColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
