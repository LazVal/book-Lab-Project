package tests;

import net.datafaker.Faker;

import java.util.Locale;

public class TestData {
    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));
    public static final String USERNAME = "leraqa888";
    public static final String PASSWORD = "leraqa888";
    public static final String WRONG_PASSWORD = "user88";
    public static final String BLANK_PASSWORD = "";
    public static final String BLANK_USERNAME = "";

    public static final String USER_ALREADY_EXIST_ERROR = "A user with that username already exists.";
    public static final String NOT_BE_BLANK_ERROR = "This field may not be blank.";
    public static final String INVALID_USERNAME_ERROR = "Invalid username or password.";
    public static final String AUTHORIZATION_ERROR = "Authorization header must contain two space-delimited values";
    public static final String EXPECTED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";



    public String bookTitle = faker.name().firstName();
    public String bookAuthor = faker.name().fullName();
    public int publicationYear = faker.number().numberBetween(1900, 2025);
    public String description = faker.name().firstName();
    public String telegramChatLink = faker.internet().url();

    public String newBookTitle = faker.name().firstName();
    public String newBookAuthor = faker.name().fullName();
}
