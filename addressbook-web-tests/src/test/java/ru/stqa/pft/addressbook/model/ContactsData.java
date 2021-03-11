package ru.stqa.pft.addressbook.model;

public class ContactsData {
    private final String city;
    private final String mobilePhone;
    private final String email;

    public ContactsData(String city, String mobilePhone, String email) {
        this.city = city;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }
}
