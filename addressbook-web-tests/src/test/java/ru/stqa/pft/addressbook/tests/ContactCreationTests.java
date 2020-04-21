package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "street","32111111", "123456@mail.com"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().gotoHomePage();
        app.logout();
    }
}
