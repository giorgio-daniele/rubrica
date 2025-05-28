package model;

import java.util.Objects;

public class Contact {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int	   age;

    public Contact() {
        this("", "", "", "", 0);
    }
    
    public Contact(String firstName, String lastName, String address, String phone, int age) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.address   = address;
        this.phone     = phone;
        this.age       = age;
    }

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact c)) return false;
        return age == c.age &&
               Objects.equals(firstName, c.firstName) &&
               Objects.equals(lastName,  c.lastName) &&
               Objects.equals(address,   c.address) &&
               Objects.equals(phone,     c.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phone, age);
    }
}
