package com.bridgelabz.addressbooksystem;

/*
 *Declaring The ContactDetails Class
 *And Declaring The Variables
 *And Defining The Getter And Setter Method
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String email;
    private int zip;
    private String phoneNumber;

    /*
     *Declaring The Parametrised Constructor
     *To Initialise The Parameters
     */
    public Contact() {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    // getter and setter method
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public int getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    /*
     *Declaring The To String Method
     *The toString() method returns the string representation of the object
     */
    @Override
    public String toString() {
        return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
                + city + ", state=" + state + ", email=" + email + ", zip=" + zip + ", phoneNumber=" + phoneNumber
                + "]";
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Contact)) return false;
        Contact person1 = (Contact) object;
        return (this.firstName.equals(person1.firstName) && this.lastName.equals(person1.lastName) && this.address.equals(person1.address) && this.city.equals(person1.city)
                && this.state.equals(person1.state) && this.phoneNumber.equals(person1.phoneNumber) && this.zip == person1.zip && this.email.equals(person1.email));
    }
}
