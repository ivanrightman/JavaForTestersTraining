package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String mobile;
    private final String email;

    public ContactData(String FirstName, String LastName, String Address, String Mobile, String Email) {
        firstName = FirstName;
        lastName = LastName;
        address = Address;
        mobile = Mobile;
        email = Email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {return address; }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
