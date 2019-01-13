package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class  NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {//Оптимизированы переходы между страницами
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Группы")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("Группы"));
  }

  public void gotoHomePage() {//Оптимизированы переходы между страницами
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("Главная"));
  }
}
