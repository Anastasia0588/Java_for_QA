package ru.stqa.pft.addressbook.model;

public class ContactData {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
    private String group;
>>>>>>> Stashed changes
=======
    private String group;
>>>>>>> Stashed changes
    private final String name;
    private final String lastName;
    private final String company;
    private final String city;
    private final String phoneNumber;
    private final String email;
    private String group;

    public ContactData(String name, String lastName, String company, String city, String phoneNumber, String email, String group) {
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}