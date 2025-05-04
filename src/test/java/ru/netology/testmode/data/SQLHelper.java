package ru.netology.testmode.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static void updateUsers(String login, String password) {
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (UUID(), ?, ?, 'active');";
        try (var conn = getConnection()) {
            runner.update(conn, dataSQL, login, password);
        }
    }

    @SneakyThrows
    public static Object countUsers() {
        var countSQL = "SELECT COUNT(*) FROM users;";
        try (var conn = getConnection()) {
            return runner.query(conn, countSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static DataHelper.User getFirstUser() {
        var usersSQL = "SELECT id, login, password, status FROM users LIMIT 1;";
        try (var conn = getConnection()) {
            return runner.query(conn, usersSQL, new BeanHandler<>(DataHelper.User.class));
        }
    }

    @SneakyThrows
    public static List<DataHelper.User> getUsers() {
        var usersSQL = "SELECT id, login, password, status FROM users;";
        try (var conn = getConnection()) {
            return runner.query(conn, usersSQL, new BeanListHandler<>(DataHelper.User.class));
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        try (var conn = getConnection()) {
            runner.update(conn, "DELETE FROM auth_codes");
            runner.update(conn, "DELETE FROM card_transactions");
            runner.update(conn, "DELETE FROM cards");
            runner.update(conn, "DELETE FROM users");
        }
    }
}
