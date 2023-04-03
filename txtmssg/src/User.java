import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    //a user consists of a person, conversations, and contacts
    private Person person;
    private ArrayList<Conversation> userConvo;
    private Contacts userContacts;

    public User(Person person){
        this.person = person;
        userContacts = new Contacts();
        userConvo = new ArrayList<Conversation>();
    }

    public User(){
        userContacts = new Contacts();
        userConvo = new ArrayList<Conversation>();
    }

    //May use these for file imports
    public void setPerson(Person person){
        this.person = person;
    }

    public void setUserConversations(ArrayList<Conversation> userConvo) {
        this.userConvo = userConvo;
    }
    public void setUserContacts(Contacts userContacts) {
        this.userContacts = userContacts;
    }

    public ArrayList<Conversation> getUserConversations(){
        return userConvo;
    }

    public Contacts getUserContacts(){
        return userContacts;
    }

    public Person getPerson() {
        return person;
    }

}
