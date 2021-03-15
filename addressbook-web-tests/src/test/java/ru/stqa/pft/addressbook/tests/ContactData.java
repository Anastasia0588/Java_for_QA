package ru.stqa.pft.addressbook.tests;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String company;
    private final String city;
    private final String phoneNumber;
    private final String email;

    public ContactData(String name, String lastName, String company, String city, String phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
