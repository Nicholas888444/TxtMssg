import java.io.Serializable;
import java.util.ArrayList;

public class Contacts implements Serializable {

    private ArrayList<Person> contacts;

    public Contacts() {
        contacts = new ArrayList<Person>();
    }

    public void addContact(Person person) {
        contacts.add(person);
    }

    public void deleteContact(int i) {
        contacts.remove(i);
    }

    public void setContacts(ArrayList<Person> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Person> getContacts() {
        return contacts;
    }
}
