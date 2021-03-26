package com.codecool.student_scores;

import java.util.Objects;

public class City {
    private final String name;
    private Integer points;

    public City(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public void addPoints(Integer points) {
        this.points += points;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
