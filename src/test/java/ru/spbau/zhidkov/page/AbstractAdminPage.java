package ru.spbau.zhidkov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.spbau.zhidkov.element.AdministrationDropdownItem;

import java.util.List;

public class AbstractAdminPage extends AbstractPage {

    private final static String HREF_ATTRIBUTE = "href";
    private final static By ADMINISTRATION_DROPDOWN_SELECTOR = By.className("ring-link ring-menu__item ring-menu__item_icon ring-menu__item_noselect ring-js-dropdown");
    private final static By ADMINISTRATION_DROPDOWN_ITEMS_SELECTOR = By.className("ring-dropdown__item ring-link");


    public AbstractAdminPage(WebDriver driver) {
        super(driver);
    }

    public void selectFromAdministrationMenu(AdministrationDropdownItem administrationDropdownItem) {
        final WebElement administrationDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(ADMINISTRATION_DROPDOWN_SELECTOR));
        administrationDropdown.click();
        final List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADMINISTRATION_DROPDOWN_ITEMS_SELECTOR));
        for (WebElement webElement : dropdownOptions) {
            if (webElement.getAttribute(HREF_ATTRIBUTE).endsWith(administrationDropdownItem.toString().toLowerCase())) {
                webElement.click();
                break;
            }
        }

    }




}
