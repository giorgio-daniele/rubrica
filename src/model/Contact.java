package model;

import java.util.Objects;


public class Contact {

    private String firstName;  // "nome"
    private String lastName;   // "cognome"
    private String address;    // "address"
    private String phone;      // "numero di telefono"
    private int    age;        // "età"

    public Contact() {
        this("", "", "", "", 0);
    }

    public Contact(
    		String firstName, 
    		String lastName, 
    		String address, 
    		String phone, int age) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.address   = address;
        this.phone     = phone;
        this.age       = age;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Contact))
            return false;
        Contact contact = (Contact) o;
        return age == contact.age &&
               Objects.equals(firstName, contact.firstName) &&
               Objects.equals(lastName,  contact.lastName) &&
               Objects.equals(address,   contact.address) &&
               Objects.equals(phone,     contact.phone);
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phone, age);
    }
}
