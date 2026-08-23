package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import api.ApiClient;
public class BaseTest {

    protected static final ApiClient api = new ApiClient();
    TestData testData = new TestData();

    @BeforeAll
    public static void setUp() {

       // RestAssured.baseURI = "https://book-club.qa.guru";
        Configuration.baseUrl = System.getProperty("URL","https://book-club.qa.guru");
    }

}

