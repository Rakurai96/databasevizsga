package com.codecool.student_scores;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

public class StudentScores {

    private String url;
    private String user;
    private String password;

    public StudentScores(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getCityWithHighestScore() {

        try (Connection connection = DriverManager.getConnection(url, user, password)){
            ArrayList<City> cities = new ArrayList<>();
            String SQL = "SELECT * FROM student_scores";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                String name = resultSet.getString("city");
                int score = resultSet.getInt("score");
                cities.add(new City(resultSet.getString("city"), resultSet.getInt("Score")));
            }
            ArrayList<City> arranged = new ArrayList<>();
            for (int i = 0; i < cities.size(); i++) {
                if(!arranged.contains(cities.get(i))) {
                    int sum = 0;
                    for (int j = 0; j < cities.size(); j++) {
                        if (cities.get(j).getName().equals(cities.get(i).getName()))
                            sum += cities.get(i).getPoints();
                    }
                    City city = new City(cities.get(i).getName(), sum);
                    arranged.add(city);
                }
            }
            for (int i = 0; i < arranged.size(); i++) {
                for (int j = i + 1; j < arranged.size(); j++) {
                    if (arranged.get(i).getPoints() < arranged.get(j).getPoints()) {
                        City swap = arranged.get(i);
                        arranged.set(i, arranged.get(j));
                        arranged.set(j, swap);
                    }
                }
            }
            if(arranged.get(0).getPoints() == arranged.get(1).getPoints()){
                if(Integer.valueOf(arranged.get(0).getName().substring(0,1)) < Integer.valueOf(arranged.get(1).getName().substring(0,1))){
                    return arranged.get(0).getName();
                } else {
                    return arranged.get(1).getName();
                }
            }
            return (arranged != null && arranged.size() != 0) ? arranged.get(0).getName() : "";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public String getMostActiveStudent() {

        try (Connection connection = DriverManager.getConnection(url, user, password)){
            ArrayList<String> students = new ArrayList<>();
            ArrayList<City> cities = new ArrayList<>();
            String SQL = "SELECT * FROM student_scores";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                students.add(resultSet.getString("student_name"));
            }

            ArrayList<String> resultString = new ArrayList<>();
            ArrayList<Integer> resultInteger = new ArrayList<>();

            for (int i = 0; i < students.size(); i++) {
                if (!resultString.contains(students.get(i))) {
                    resultString.add(students.get(i));
                    int sum = 1;

                    for (int j = 0; j < students.size(); j++) {
                        if (i != j && students.get(i).equals(students.get(j)))
                            sum += 1;
                    }
                    resultInteger.add(sum);
                }
            }
            for (int i = 0; i < resultString.size(); i++) {
                for (int j = i + 1; j < resultString.size(); j++) {
                    if (resultInteger.get(i) < resultInteger.get(j)) {
                        String swapString = resultString.get(i);
                        resultString.set(i, resultString.get(j));
                        resultString.set(j, swapString);

                        Integer swapInteger = resultInteger.get(i);
                        resultInteger.set(i, resultInteger.get(j));
                        resultInteger.set(j, swapInteger);
                    }
                }
            }
            return (resultString != null && resultString.size() != 0) ? resultString.get(0) : "";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
}
