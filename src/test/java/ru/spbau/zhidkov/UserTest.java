package ru.spbau.zhidkov;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.zhidkov.element.AdministrationDropdownItem;
import ru.spbau.zhidkov.element.CreateUserDialog;
import ru.spbau.zhidkov.page.LoginPage;
import ru.spbau.zhidkov.page.MainPage;
import ru.spbau.zhidkov.page.UserPage;
import ru.spbau.zhidkov.utils.User;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private final static String URL = "http://localhost:8080";
    private final static User ROOT = new User("root", "root");
    private final static int MAX_LOGIN_LEN = 50;

    private final static By EXCLAMATION_POINT_SELECTOR = By.className("error-bulb2");
    private final static By MESSAGE_ERROR_SELECTOR = By.className("message error");

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void beforeTest() {
        driver = new SafariDriver();
        wait = new WebDriverWait(driver, 2);

        driver.get(URL);
        final LoginPage loginPage = new LoginPage(driver);

        loginPage.setLogin(ROOT.getLogin());
        loginPage.setPassword(ROOT.getPassword());
        loginPage.clickLogIn();

        final MainPage mainPage = new MainPage(driver);
        mainPage.selectFromAdministrationMenu(AdministrationDropdownItem.USERS);

    }


    @Test
    public void testContainsRoot() {
        final UserPage userPage = new UserPage(driver);
        assertTrue(userPage.containsUser(ROOT.getLogin()));
    }

    @Test
    public void testEnglishUser() {
        testDefaultScenario(new User("login", "password"));
    }

    @Test
    public void testRussianUser() {
        testDefaultScenario(new User("логин", "пароль"));
    }

    @Test
    public void testUserWithSymbols() {
        testDefaultScenario(new User("'+)*&", "password"));
    }

    @Test
    public void testOneSymbol() {
        testDefaultScenario(new User("a", "password"));
    }

    @Test
    public void testMaxSymbols() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX_LOGIN_LEN; i++) {
            sb.append('a');
        }
        testDefaultScenario(new User(sb.toString(), "password"));
    }


    @Test
    public void testEmptyUser() {
        testErrorScenario(new User("", "password"), this::waitForExclamationPoint);
    }

    @Test
    public void testSpaceInTheBeginning() {
        testErrorScenario(new User(" user", "password"), this::waitForMessageError);
    }

    @Test
    public void testSpaceInTheMiddle() {
        testErrorScenario(new User("us er", "password"), this::waitForMessageError);
    }

    @Test
    public void testSpaceInTheEnd() {
        testErrorScenario(new User("user ", "password"), this::waitForMessageError);
    }


    private void testDefaultScenario(User user) {
        final UserPage userPage = new UserPage(driver);
        userPage.createUser(user);
        final MainPage mainPage = new MainPage(driver);
        wait.until(ExpectedConditions.urlContains("editUser"));
        mainPage.selectFromAdministrationMenu(AdministrationDropdownItem.USERS);
        assertTrue(userPage.containsUser(user.getLogin()));
        userPage.deleteUser(user.getLogin());
        assertFalse(userPage.containsUser(user.getLogin()));
    }

    private void testErrorScenario(User user, Runnable check) {
        final UserPage userPage = new UserPage(driver);
        userPage.createUser(user);
        check.run();
    }


    private void waitForExclamationPoint() {
        final WebElement exclamationPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(EXCLAMATION_POINT_SELECTOR));
        new CreateUserDialog(driver).cancel();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(exclamationPoint)));
    }

    private void waitForMessageError() {
        final WebElement messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_ERROR_SELECTOR));
        new CreateUserDialog(driver).cancel();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(messageError)));
    }

    @After
    public void closeDriver() {
        driver.quit();
    }


}
