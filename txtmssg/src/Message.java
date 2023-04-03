import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message implements Serializable {
    //Time and date
    private StringBuilder time = new StringBuilder();
    LocalDateTime now;
    //Initialize the rest of the variables
    private String content;
    private String subject;
    private Person sender;
    private Person receiver;
    private boolean keepOrNot;


    //Set methods
    public void setContent(String content) { this.content = content; }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setSender(Person sender) {
        this.sender = sender;
    }
    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
    public void setKeepOrNot(boolean keepOrNot) {this.keepOrNot = keepOrNot;}

    //Add the date
    public void setTimeSent() {
        now = LocalDateTime.now();
        time.append(now);
        //Modify the date
        String hold = time.substring(0,4);
        time.delete(0,5);
        time.insert(5,"-" + hold + " ");

        //Modify the time(to standard time)
        time.delete(20,30);
        String standard = time.substring(12,14);
        if(standard.equals("13")) {
            time.replace(12,14,"1");
            time.append(" p.m.");}
        else if(standard.equals("14")) {
            time.replace(12,14,"2");
            time.append(" p.m.");}
        else if(standard.equals("15")) {
            time.replace(12,14,"3");
            time.append(" p.m.");}
        else if(standard.equals("16")) {
            time.replace(12,14,"4");
            time.append(" p.m.");}
        else if(standard.equals("17")) {
            time.replace(12,14,"5");
            time.append(" p.m.");}
        else if(standard.equals("18")) {
            time.replace(12,14,"6");
            time.append(" p.m.");}
        else if(standard.equals("19")) {
            time.replace(12,14,"7");
            time.append(" p.m.");}
        else if(standard.equals("20")) {
            time.replace(12,14,"8");
            time.append(" p.m.");}
        else if(standard.equals("21")) {
            time.replace(12,14,"9");
            time.append(" p.m.");}
        else if(standard.equals("22")) {
            time.replace(12,14,"10");
            time.append(" p.m.");}
        else if(standard.equals("23")) {
            time.replace(12,14,"11");
            time.append(" p.m.");}
        else if(standard.equals("24")) {
            time.replace(12,14,"12");
            time.append(" p.m.");}
        else {
            time.append(" a.m.");}
        time.deleteCharAt(11);
    }

    //All the get methods
    public String getContent() {return content;}
    public Person getSender() {return sender;}
    public Person getReceiver() {return receiver;}
    public String getSubject() {return subject;}
    public String getTimeSent() {return time.toString();}
    public boolean getkeepOrNot() {return keepOrNot;}


    public Message(Person sender, Person receiver) {
        this.sender = sender;
        this.receiver = receiver;
        keepOrNot = false;
        setTimeSent();
    }

    //May or may not use these
    public Message() {
        setTimeSent();
        keepOrNot = false;
    }

    public Message(Person sender, Person receiver, String subject, String content) {
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        keepOrNot = false;
        setTimeSent();
    }

    //Displays the message in an easy-to-read format
    public String display() {
        return("Subject: " + subject + "\n" +
                sender.getFirstName() + ": " + content + "\n" +
                "Sent at: " + time + "\n");
    }

}
