package ru.spbau.zhidkov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    private final static By loginOrEmailSelector = By.name("l.L.login");
    private final static By passwordSelector = By.name("l.L.password");
    private final static By logInSelector = By.id("id_l.L.loginButton");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setLogin(String login){
        final WebElement loginOrEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginOrEmailSelector));
        loginOrEmailField.sendKeys(login);
    }

    public void setPassword(String password){
        final WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordSelector));
        passwordField.sendKeys(password);
    }

    public void clickLogIn(){
        final WebElement logInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(logInSelector));
        logInBtn.click();
    }

}
