package ru.spbau.zhidkov.element;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.spbau.zhidkov.utils.AbstractObject;

import java.util.List;

public class UsersTable extends AbstractObject {

    private final static String TITLE_ATTRIBUTE = "title";
    private final static String P0_ATTRIBUTE = "p0";
    private final static By USER_LOGIN_SELECTOR = By.cssSelector("a[id^='id_l.U.usersList.UserLogin']");
    private final static By DELETE_USER_SELECTOR = By.cssSelector("a[id^='id_l.U.usersList.deleteUser']");

    public UsersTable(WebDriver driver) {
        super(driver);
    }

    public boolean contains(String login) {
        final List<WebElement> usersItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_LOGIN_SELECTOR));
        return usersItems.stream().anyMatch(userItem -> userItem.getAttribute(TITLE_ATTRIBUTE).equals(login));
    }

    public void delete(String login) {
        final String p0 = getP0(login);
        final List<WebElement> deleteItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DELETE_USER_SELECTOR));
        deleteItems.removeIf(webElement -> !webElement.getAttribute(P0_ATTRIBUTE).equals(p0));
        for (WebElement webElement : deleteItems) {
            webElement.click();
            wait.until(ExpectedConditions.alertIsPresent());
            final Alert alert = driver.switchTo().alert();
            alert.accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
        }
    }

    private String getP0(String login) {
        final List<WebElement> usersItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_LOGIN_SELECTOR));
        usersItems.removeIf(userItem -> !userItem.getAttribute(TITLE_ATTRIBUTE).equals(login));
        if (usersItems.size() == 0) {
            throw new IllegalStateException("no user with login " + login);
        }
        return usersItems.get(0).getAttribute(P0_ATTRIBUTE);
    }


}
