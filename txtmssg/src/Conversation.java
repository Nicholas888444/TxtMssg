import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {

    private ArrayList<Message> conversation;
    private ArrayList<Message> archive;
    private String subject;
    private Person sender;
    private Person receiver;
    private String conversationID;


    //Constructor
    public Conversation() {
        conversation = new ArrayList<Message>();
        archive = new ArrayList<Message>();
    }

    public Conversation(Person sender, Person receiver) {
        this.sender = sender;
        this.receiver = receiver;
        conversation = new ArrayList<Message>();
        archive = new ArrayList<Message>();
    }
    public Conversation(Person sender, Person receiver, String subject, String conversationID) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.conversationID = conversationID;
        conversation = new ArrayList<Message>();
        archive = new ArrayList<Message>();
    }


    //Adds a message object
    public void addMessage(Message message) {
        conversation.add(message);
        //Automatically archives a message if the value is set to keep
        if(message.getkeepOrNot() == true) {
            archive.add(message);
        }
    }
    public void addArchive(Message message) {
        archive.add(message);
    }

    public void deleteMessage(int index) {
        conversation.remove(index);
    }
    public void deleteArchivedMessage(int index) {
        archive.remove(index);
    }

    //Setters and getters
    public void setConversation(ArrayList conversation) {this.conversation = conversation;}
    public ArrayList<Message> getConversation() {return conversation;}

    public void setArchive(ArrayList archive) {this.archive = archive;}
    public ArrayList<Message> getArchive() {return archive;}

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }
}
