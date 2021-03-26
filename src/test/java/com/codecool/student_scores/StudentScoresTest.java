package com.codecool.student_scores;

import cccr.CCCRTestExecutionListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({CCCRTestExecutionListener.class})
public class StudentScoresTest {
    private static final String STUDENT_ANNE = "Anne Small";
    private static final String STUDENT_JOHN = "John Doe";
    private static final String STUDENT_CLARK = "Clark Kent";
    private static final String STUDENT_MARK = "Mark Rover";
    private static final String CITY_W = "Washington";
    private static final String CITY_N = "New York City";
    private static final String CITY_B = "Boston";
    private static final String CITY_T = "Texas";

    private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private StudentScores studentScores;

    @BeforeEach
    void init() {
        studentScores = new StudentScores(DB_URL, DB_USER, DB_PASSWORD);
        initTables();
    }

    @Test
    void test_getCityWithHighestScore_emptyDatabase() {
        assertEquals("", studentScores.getCityWithHighestScore());
    }

    @Test
    void test_getMostActiveStudent_emptyDatabase() {
        assertEquals("", studentScores.getMostActiveStudent());
    }

    @Test
    void test_getMostActiveStudent_everyStudentOnlyOnce() {
        createDummyData();
        assertEquals(STUDENT_ANNE, studentScores.getMostActiveStudent());
    }

    @Test
    void test_getMostActiveStudent_AnneTwice() {
        createDummyData();
        addExtraData();
        assertEquals(STUDENT_ANNE, studentScores.getMostActiveStudent());
    }

    @Test
    void test_getCityWithHighestScore_everyoneOnce() {
        createDummyData();
        assertEquals(CITY_N, studentScores.getCityWithHighestScore());
    }

    @Test
    void test_getCityWithHighestScore_draw() {
        createDummyData();
        addExtraData();
        assertEquals(CITY_N, studentScores.getCityWithHighestScore());
    }

    @AfterEach
    void destruct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DROP TABLE IF EXISTS student_scores";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void initTables() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "CREATE TABLE IF NOT EXISTS student_scores (" +
                    "city VARCHAR(255), " +
                    "student_name VARCHAR(255), " +
                    "score INT" +
                    ");";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createDummyData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO student_scores VALUES ('" + CITY_B + "', '" + STUDENT_CLARK + "', 20)";
            Statement statement = connection.createStatement();
            statement.execute(query);
            query = "INSERT INTO student_scores VALUES ('" + CITY_W + "', '" + STUDENT_ANNE + "', 30)";
            statement = connection.createStatement();
            statement.execute(query);
            query = "INSERT INTO student_scores VALUES ('" + CITY_N + "', '" + STUDENT_JOHN + "', 50)";
            statement = connection.createStatement();
            statement.execute(query);
            query = "INSERT INTO student_scores VALUES ('" + CITY_T + "', '" + STUDENT_MARK + "', 40)";
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void addExtraData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO student_scores VALUES ('" + CITY_W + "', '" + STUDENT_ANNE + "', 20)";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
