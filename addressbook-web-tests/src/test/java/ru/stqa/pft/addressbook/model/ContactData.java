package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  int id;
    private final String name;
    private final String lastName;
    private final String company;
    private final String city;
    private final String phoneNumber;
    private final String email;
    private String group;

    public ContactData(int id, String name, String lastName, String company, String city, String phoneNumber, String email, String group) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }



    public ContactData(String name, String lastName, String company, String city, String phoneNumber, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }


    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }


}