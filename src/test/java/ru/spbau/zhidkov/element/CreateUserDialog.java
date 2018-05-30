package ru.spbau.zhidkov.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.spbau.zhidkov.utils.AbstractObject;

public class CreateUserDialog extends AbstractObject {

    private final static By LOGIN_SELECTOR = By.id("id_l.U.cr.login");
    private final static By PASSWORD_SELECTOR = By.id("id_l.U.cr.password");
    private final static By CONFIRM_PASSWORD_SELECTOR = By.id("id_l.U.cr.confirmPassword");
    private final static By CREATE_USER_BUTTON_SELECTOR = By.id("id_l.U.cr.createUserOk");
    private final static By CANCEL_BUTTON_SELECTOR = By.id("id_l.U.cr.createUserCancel");


    public CreateUserDialog(WebDriver driver) {
        super(driver);
    }

    public void setLogin(String login) {
        final WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_SELECTOR));
        loginField.sendKeys(login);
    }

    public void setPassword(String password) {
        final WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_SELECTOR));
        passwordField.sendKeys(password);
    }

    public void confirmPassword(String password) {
        final WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_PASSWORD_SELECTOR));
        passwordField.sendKeys(password);
    }

    public void createUser() {
        final WebElement createUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_USER_BUTTON_SELECTOR));
        createUserBtn.click();
    }

    public void cancel() {
        final WebElement createUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(CANCEL_BUTTON_SELECTOR));
        createUserBtn.click();
    }
}
