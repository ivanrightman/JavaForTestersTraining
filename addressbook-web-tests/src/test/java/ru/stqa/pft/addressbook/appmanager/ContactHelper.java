package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactHelper extends HelperBase {
    public ContactHelper(FirefoxDriver wd){
        super(wd);
    }

    public void initContactCreation() {click(By.linkText("add new"));}

    public void fillContactForm(ContactData contactData) {
        type(By.cssSelector("input[name=\"firstname\"]"), contactData.getFirstName());
        type(By.cssSelector("input[name=\"lastname\"]"), contactData.getLastName());
        type(By.cssSelector("textarea[name=\"address\"]"), contactData.getAddress());
        type(By.cssSelector("input[name=\"mobile\"]"), contactData.getMobile());
        type(By.cssSelector("input[name=\"email\"]"), contactData.getEmail());
    }

    public void submitContactCreation(){
        click (By.cssSelector("input[name=\"submit\"]"));
    }
    public void gotoHomePage() {
        click(By.linkText("home"));
    }

}
