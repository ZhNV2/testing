package ru.spbau.zhidkov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.spbau.zhidkov.element.CreateUserDialog;
import ru.spbau.zhidkov.element.UsersTable;
import ru.spbau.zhidkov.utils.User;

public class UserPage extends AbstractAdminPage {

    private final static By CREATE_USER_SELECTOR = By.id("id_l.U.createNewUser");
    private final UsersTable usersTable;


    public UserPage(WebDriver driver) {
        super(driver);
        usersTable = new UsersTable(driver);
    }

    public void createUser(User user) {
        final WebElement createUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_USER_SELECTOR));
        createUserBtn.click();
        final CreateUserDialog createUserDialog = new CreateUserDialog(driver);
        createUserDialog.setLogin(user.getLogin());
        createUserDialog.setPassword(user.getPassword());
        createUserDialog.confirmPassword(user.getPassword());
        createUserDialog.createUser();
    }

    public void openCreationDialog() {
        final WebElement createUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_USER_SELECTOR));
        createUserBtn.click();
    }

    public boolean containsUser(String login) {
        return usersTable.contains(login);
    }

    public void deleteUser(String login) {
        usersTable.delete(login);
    }
}
