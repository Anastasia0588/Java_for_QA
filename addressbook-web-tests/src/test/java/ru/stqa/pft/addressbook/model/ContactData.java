package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  int id = Integer.MAX_VALUE;
    private String name;
    private String lastName;
    private String company;
    private String city;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String allPhones;
    private String email;
    private String group;

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withCity(String city) {
        this.city = city;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }


    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
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

    public String getMobilephone() {
        return mobilephone;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }


    public String getAllPhones() {
        return allPhones;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }



}