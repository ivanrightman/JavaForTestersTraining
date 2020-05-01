package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.cssSelector("input[name=\"firstname\"]"), contactData.getFirstName());
        type(By.cssSelector("input[name=\"lastname\"]"), contactData.getLastName());
        type(By.cssSelector("textarea[name=\"address\"]"), contactData.getAddress());
        type(By.cssSelector("input[name=\"mobile\"]"), contactData.getMobile());
        type(By.cssSelector("input[name=\"email\"]"), contactData.getEmail());
        type(By.cssSelector("input[name=\"email2\"]"), contactData.getEmail2());
        type(By.cssSelector("input[name=\"email3\"]"), contactData.getEmail3());
        type(By.cssSelector("input[name=\"home\"]"), contactData.getHome());
        type(By.cssSelector("input[name=\"work\"]"), contactData.getWork());
        //attache(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.cssSelector("input[name=\"submit\"]"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//input[@value='" + id + "']/../../td/a/img[@alt='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void selectAllContacts() {
        wd.findElement(By.cssSelector("input[id='MassCB']")).click();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        gotoHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        editContact(contact);
        contactCache = null;
        gotoHomePage();
    }

    public void editContact(ContactData contact) {
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptDeleteContact();
        contactCache = null;
        gotoHomePage();
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> entryparts = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(entryparts.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = entryparts.get(2).getText();
            String lastName = entryparts.get(1).getText();
            String address = entryparts.get(3).getText();
            String allEmails = entryparts.get(4).getText();
            String allPhones = entryparts.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAddress(address).withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withFirstName(lastName).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withHomephone(home).withMobile(mobile).withWorkPhone(work);
    }

    public void selectGroup(String group, String element) {
        new Select(wd.findElement(By.name(element))).selectByVisibleText(group);
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectGroup("[all]", "group");
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        click(By.name("add"));
        gotoHomePage();
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectGroup("[all]", "group");
        selectGroup(group.getName(), "group");
        selectAllContacts();
        //selectContactById(contact.getId());
        click(By.name("remove"));
    }
}

