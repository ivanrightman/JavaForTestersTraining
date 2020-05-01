package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class AddContactToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstName("firstname")
                    .withLastName("lastname")
                    .withAddress("address")
                    .withMobile("mobile")
                    .withEmail("email")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHomephone("home")
                    .withWorkPhone("work")
                    .inGroup(groups.iterator().next())
            );
        }
    }

    @Test
    public void testAddContactToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups allGroups = app.db().groups();
        GroupData addedGroup = allGroups.iterator().next();
        if (allGroups.equals(contact.getGroups())) {
            app.contact().removeFromGroup(contact, addedGroup);
        }
        app.goTo().homePage();
        app.contact().addToGroup(contact, addedGroup);
        app.db().sessionRefresh(contact);
        Groups fromContacts = contact.getGroups();
        GroupData contactInGroup = fromContacts.iterator().next();
        assertThat(contactInGroup, is(addedGroup));
    }
}
