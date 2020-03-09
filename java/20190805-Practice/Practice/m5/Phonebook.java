import java.util.*;

public class Phonebook
{
    private String owner;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public Phonebook(String owner) {
        this.owner = owner;
    }
    
    public void addContact(Contact c) {
        this.contacts.add(c);
    }
    
    public void show() {
        System.out.println(this.owner + "'s phonebook");
        for (Contact c: contacts) {
            System.out.println(c.toString());
        }
    }
    
    public Contact findContactByName(String name) {
        for (Contact c: contacts) {
            if (c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
}