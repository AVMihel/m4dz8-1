package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private static final Faker FAKER = new Faker();

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class User {
        String id;
        String login;
        String password;
        String status;
    }

    public static String generateRandomLogin() {
        return FAKER.name().username();
    }

    public static String generateRandomPassword() {
        return FAKER.internet().password();
    }
}