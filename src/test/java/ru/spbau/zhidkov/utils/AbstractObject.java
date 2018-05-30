package ru.spbau.zhidkov.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractObject {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 2);
    }
}
