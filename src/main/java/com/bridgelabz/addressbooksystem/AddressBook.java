package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddressBook {
    private ArrayList<Contact> contactBook = new ArrayList<Contact>();
    public HashMap<String, ArrayList<Contact>> personsByCity = new HashMap<String, ArrayList<Contact>>();
    public  HashMap<String, ArrayList<Contact>> personsByState = new HashMap<String, ArrayList<Contact>>();

    Scanner sc = new Scanner(System.in);
    private static int numberOfConatcts = 0;
    private String adressBookName;


    @Override
    public String toString() {
        return "AddressBook [adressBookName=" + adressBookName + "]";
    }


    public String getAdressBookName() {
        return adressBookName;
    }


    public void setAdressBookName(String adressBookName) {
        this.adressBookName = adressBookName;
    }
    public String nameString = this.adressBookName+".txt";
    public void write() {
        AddressBookFileIO.writeData(contactBook, this.adressBookName+".txt");
    }

    public List<String> read() {
        return AddressBookFileIO.readDataFromFile(this.adressBookName);
    }

    public void addContacts()
    {
        System.out.println("Enter Person details:");

        Contact person = details();
        boolean isDuplicate = contactBook.stream().anyMatch(contact -> contact.equals(person));
        if(isDuplicate) {
            System.out.println("Duplicate data entry. discarded");
        }
        else
        {
            contactBook.add(person);
            if(personsByCity.get(person.getCity()) == null) personsByCity.put(person.getCity(), new ArrayList<>());
            personsByCity.get(person.getCity()).add(person);
            if(personsByState.get(person.getState()) == null) personsByState.put(person.getState(), new ArrayList<>());
            personsByState.get(person.getState()).add(person);
        }


    }


    public void edit()
    {
        System.out.println("enter the name to edit contact details");
        String name = sc.next();
        System.out.println("enter the choice to edit details");
        System.out.println("1.First Name\\n2.Last Name\\n3.City\\n4.State\\n5.Zip Code\\n6.Phone\\n7.Email");
        int choice = sc.nextInt();
        int index =0;
        for( index =0;index<numberOfConatcts;index++)
            if(name.equals(contactBook.get(index).getFirstName()))
            {
                System.out.println("name exists , now enter the new details");

                break;
            }
            else {
                System.out.println("No contact found");
                return;
            }
        switch (choice) {
            case 1:
                System.out.println("Enter new First Name");
                String newFirstName = sc.next();
                contactBook.get(index).setFirstName(newFirstName);
                break;
            case 2:
                System.out.println("Enter new Last Name");
                String newLastName = sc.next();
                contactBook.get(index).setLastName(newLastName);
                break;
            case 3:
                System.out.println("Enter new City");
                String newCity = sc.next();
                contactBook.get(index).setCity(newCity);
                break;
            case 4:
                System.out.println("Enter new State");
                String newState = sc.next();
                contactBook.get(index).setState(newState);
                break;
            case 5:
                System.out.println("Enter new State");
                int newZip = sc.nextInt();
                contactBook.get(index).setZip(newZip);
                break;
            case 6:
                System.out.println("Enter new Phone Number");
                String newPNumber = sc.next();
                contactBook.get(index).setPhoneNumber(newPNumber);
                break;
            case 7:
                System.out.println("Enter new Email");
                String newEmail = sc.next();
                contactBook.get(index).setEmail(newEmail);
                break;
        }


    }

    public void delete()
    {
        int index;
        System.out.println("Enter the name of the contact to delete");
        String name = sc.next();
        for( index=0;index<numberOfConatcts;index++)
            if(contactBook.get(index).getFirstName().equals(name)) {
                break;
            }
        while(!contactBook.get(index+1).equals(null)) {
            contactBook.set(index, contactBook.get(index+1));
            index++;
        }
        contactBook.set(index,null);
        System.out.println("Deleted details of : "+ name);
    }
    public void display()
    {
        Contact person;
        System.out.println("Enter name to see details");
        String name = sc.next();

        for(int i = 0;i<contactBook.size();i++) {
            if(contactBook.get(i).getFirstName().equals(name)) {
                person = contactBook.get(i);
                System.out.println(person);
            }
        }
    }
    private static Contact details() {
        Scanner sc = new Scanner(System.in);
        Contact person1 = new Contact();

        System.out.println("Enter firstName:");
        person1.setFirstName(sc.next());
        System.out.println("Enter SecondName:");
        person1.setLastName(sc.next());
        System.out.println("Enter Address:");
        person1.setAddress(sc.next());
        System.out.println("Enter City:");
        person1.setCity(sc.next());
        System.out.println("Enter State:");
        person1.setState(sc.next());
        System.out.println("Enter Pin code:");
        person1.setZip(sc.nextInt());
        System.out.println("Enter Phone number:");
        person1.setPhoneNumber(sc.next());
        System.out.println("Enter email:");
        person1.setEmail(sc.next());
        return person1;
    }
    public void searchByCity(String city,String firstName) {
        Predicate<Contact> searchPerson = (contact -> contact.getCity().equals(city)&& contact.getFirstName().equals(firstName));
        contactBook.stream().filter(searchPerson).forEach(person -> output(person));
    }

    public void searchByState(String state, String firstName) {
        Predicate<Contact> searchPerson = (contact -> contact.getState().equals(state)&& contact.getFirstName().equals(firstName));
        contactBook.stream().filter(searchPerson).forEach(person -> output(person));
    }
    public void personsInCity(String city) {
        ArrayList<Contact> list = personsByCity.get(city);
        list.stream().forEach(person -> output(person));
    }

    public void personsInState(String State) {
        ArrayList<Contact> list = personsByState.get(State);
        list.stream().forEach(person -> output(person));
    }
    public  void sortByFirstName() {
        contactBook.stream()
                .sorted((contact1,contact2) -> contact1.getFirstName().compareTo(contact2.getFirstName()))
                .forEach(System.out::println);
    }
    public  void sortByZip() {
        contactBook.stream()
                .sorted((contact1,contact2) -> contact1.getZip()-contact2.getZip())
                .forEach(System.out::println);
    }
    public  void sortByCity() {
        contactBook.stream()
                .sorted((contact1,contact2) -> contact1.getCity().compareTo(contact2.getCity()))
                .forEach(System.out::println);
    }
    public  void sortByState() {
        contactBook.stream()
                .sorted((contact1,contact2) -> contact1.getState().compareTo(contact2.getState()))
                .forEach(System.out::println);
    }

    private static void output(Contact person) {
        System.out.println("firstName : "+person.getFirstName());
        System.out.println("SecondName : "+ person.getLastName());
        System.out.println("Address : "+ person.getAddress());
        System.out.println("City : "+person.getCity());
        System.out.println("State : "+person.getState());
        System.out.println("Pin code : "+person.getZip());
        System.out.println("Phone nmber : "+person.getPhoneNumber() );
        System.out.println("email : "+person.getEmail());
    }
}
