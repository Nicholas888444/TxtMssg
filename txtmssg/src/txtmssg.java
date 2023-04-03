import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class txtmssg {
    //static allows methods outside main method to access and modify users
    static ArrayList<User> users = new ArrayList<>();
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        //For user input
        Scanner scan = new Scanner(System.in);
        int input = -1;
        boolean flag, outter = false;
        File file = new File("Users.dat");


        //Starting Users
        //Maybe we can import these from a file
        User Nick = new User(new Person("Nicholas", "Thomson", "5884", "Mrbarrell2"));
        User Hobee = new User(new Person("Hobee", "Hembree", "Login", "password"));
        User Seth = new User(new Person("Seth", "Stephens", "2000", "Concrete"));
        User Carter = new User(new Person("Carter", "Condo", "6969","MoneyMan"));
        User Joseline = new User(new Person("Joseline", "Hernandez","7119","Panera"));
        users.add(Nick);
        users.add(Hobee);
        users.add(Seth);
        users.add(Carter);
        users.add(Joseline);
        //Adds all the users defined above in their contacts
        for(User x : users) {
            for(User y : users ) {
                if(!x.equals(y))
                    x.getUserContacts().addContact(y.getPerson());
            }
        }


        //currentUser will be the user who is logged in
        while(!outter) {
            User currentUser = new User();
            System.out.println("Welcome to txtmssg!");
            System.out.println("Enter one of the following options: ");
            flag = false;
            while (!flag) {
                System.out.println("1. Login");
                System.out.println("2. Create new user");
                System.out.println("3. Upload");
                System.out.println("4. Download");
                System.out.println("5. Exit txtmssg");

                System.out.print("Enter: ");
                input = scan.nextInt();
                System.out.println("--------------------------------------------");
                if (input == 1) {
                    currentUser = logIn();
                    flag = true;
                    System.out.println("--------------------------------------------");
                } else if (input == 2) {
                    users.add(createUser());
                    System.out.println("--------------------------------------------");
                } else if (input == 3) {
                    Upload(file);
                    System.out.println("Users were uploaded.");
                } else if (input == 4) {
                    Download(file);
                    System.out.println("Users were downloaded.");
                } else if (input == 5) {
                    flag = true;
                    outter = true;
                } else {
                    System.out.println("Please enter a valid option.");
                    System.out.println("--------------------------------------------");
                }
            }
            if ((input != 5) && (currentUser != null)) {
                System.out.println("Welcome to txtmssg!");
                System.out.println("logged in on this date: " + LocalDate.now());
                System.out.println("--------------------------------------------");
                flag = false;
                while (!flag) {
                    System.out.println("1. Contacts Manager");
                    System.out.println("2. Conversation Manager");
                    System.out.println("3. Logout");
                    System.out.print("Enter: ");
                    input = scan.nextInt();
                    System.out.println("--------------------------------");
                    if (input == 1) {
                        contactManager(currentUser);
                    } else if (input == 2) {
                        System.out.println("Here are the conversations.");
                        conversationManager(currentUser);
                    } else if (input == 3) {
                        flag = true;
                        //This saves everything that was done to this user
                        for (int x = 0; x < users.size(); x++) {
                            if (currentUser.getPerson().getLoginID().equals(users.get(x).getPerson().getLoginID())) {
                                users.add(x, currentUser);
                                users.remove(x + 1);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Please enter a valid option.");
                    }
                }
            } else {
                if(currentUser != null) {
                    System.out.println("Shutting down...");
                    System.out.println("Goodbye");
                }
            }
        }

        //TO-DO
        //Archive messages
        //Import users from a file
        //Save everything to a file
        //Update from a file
        //Encryption/Decryption

    }

    public static User createUser() {
        Scanner scan = new Scanner(System.in);
        String firstName, lastName, loginID, password, loginID2, password2;
        boolean flag;
        Person person = new Person();

        boolean valid = false;
        System.out.print("Enter your first name: ");
        while(!valid) {
            firstName = scan.nextLine();
            if (!(firstName.equals(""))) {
                person.setFirstName(firstName);
                valid = true;
            } else {
                System.out.print("Enter a valid first name: ");
            }
        }
        valid = false;
        System.out.print("Enter your last name: ");
        while(!valid) {
            lastName = scan.nextLine();
            if (!lastName.equals("")) {
                person.setLastName(lastName);
                valid = true;
            } else {
                System.out.print("Enter a valid last name: ");
            }
        }
        valid = false;
        while(!valid) {
            System.out.print("Enter a login ID: ");
            flag = false;
            loginID = scan.nextLine();
            if (!loginID.equals("")) {
                for(User user : users) {
                    if(user.getPerson().getLoginID().equals(loginID)) {
                        flag = true;
                    }
                }
                if(!flag) {
                    System.out.print("Reenter the login ID: ");
                    loginID2 = scan.nextLine();
                    if (loginID2.equals(loginID)) {
                        person.setLoginID(loginID);
                        valid = true;
                    } else {
                        System.out.println("Login ID's did not match. Retry.");
                    }
                } else {
                    System.out.println("Login ID already in use! Pick a different ID.");
                }
            } else {
                System.out.print("Enter a valid login ID: ");
            }
        }
        valid = false;
        System.out.print("Enter a password: ");
        while(!valid) {
            password = scan.nextLine();
            if (!password.equals("")) {
                System.out.print("Reenter the password: ");
                password2 = scan.nextLine();
                if(password2.equals(password)) {
                    person.setPassword(password);
                    valid = true;
                } else {
                    System.out.println("Passwords did not match! Retry.");
                    System.out.print("Enter a password:");
                }
            } else {
                System.out.print("Enter a valid password: ");
            }
        }
        System.out.println("User created!");
        return(new User(person));
    }
    public static User logIn() {
        Scanner scan = new Scanner(System.in);
        String userID = "", password;
        int count;
        Person person = new Person();
        boolean flag, main = false;

        while(!main) {
            flag = false;
            while(!flag) {
                System.out.print("Enter your user ID: ");
                userID = scan.nextLine();
                if(userID.equals("")) {
                    break;
                }
                for (User user : users) {
                    if (user.getPerson().getLoginID().equals(userID)) {
                        person = user.getPerson();
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("Please enter an existing user ID.");
                    System.out.println("Press the enter key to exit");
                }
            }
            if(userID.equals("")){
                break;
            }
            flag = false;
            count = 0;
            while(!flag && count < 3) {
                System.out.print("Enter the password for " + person.getFirstName() + ": ");
                password = scan.nextLine();
                if(person.getPassword().equals(password)) {
                    flag = true;
                    main = true;
                }
                //If user gets the password wrong 3 times, it restarts this method
                //Maybe add a way to lockout an account if unsuccessful
                if (!flag) {
                    count++;
                    System.out.println("INCORRECT PASSWORD");
                    if(count != 3)
                        System.out.println("You have " + (3-count) + " more attempt(s).");
                }
            }
            if(main) {
                System.out.println("You have logged in as " + person.getFirstName() + " " + person.getLastName());
                for(User user : users) {
                    if(user.getPerson().equals(person)) {
                        return(user);
                    }
                }
            }
        }
        //Returns this if user enters "" for the userID
        return null;
    }

    public static void contactManager(User currentUser) {
        Scanner scan = new Scanner(System.in);
        int input;
        String login;
        boolean flag = false;
        while(!flag) {
            //contactManagement(currentUser);
            System.out.println("Welcome to the Contacts Manager");
            System.out.println("1. View Current Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Remove Contact");
            System.out.println("4. Exit");
            System.out.print("Enter: ");
            input = scan.nextInt();
            System.out.println("--------------------------------");
            if (input == 1) {
                displayContacts(currentUser);
            } else if (input == 2) {
                System.out.print("Enter the login ID of the person you want to add: ");
                login = scan.nextLine();
                System.out.println();
                login = scan.nextLine();
                addContact(currentUser, login);
            } else if (input == 3) {
                System.out.println("Enter the loginID of the person you want to remove: ");
                login = scan.nextLine();
                System.out.println();
                login = scan.nextLine();
                deleteContact(currentUser, login);
            } else if (input == 4) {
                flag = true;
            } else {
                System.out.println("Enter a valid option. ");
                System.out.println("--------------------------------");
            }
        }
    }
    //These methods can edit the current users contacts
    public static void displayContacts(User currentUser) {
        System.out.println("Here are your current contacts: ");
        for (int x = 0; x < currentUser.getUserContacts().getContacts().size(); x++) {
            System.out.println(currentUser.getUserContacts().getContacts().get(x).getFirstName() + " "
                    + currentUser.getUserContacts().getContacts().get(x).getLastName() + " [" +
                    currentUser.getUserContacts().getContacts().get(x).getLoginID() + "]");
        }
        System.out.println("--------------------------------");
    }
    public static void addContact(User currentUser, String str) {
        Person display = new Person();
        boolean found = false;
        boolean flag;
        for (User user : users) {
            flag = false;
            if ((str.equals(user.getPerson().getLoginID())) && !(str.equals(currentUser.getPerson().getLoginID()))) {
                //This is to ensure that the user does not enter a repeat user in their contacts
                for(int x = 0; x < currentUser.getUserContacts().getContacts().size(); x++) {
                    if (currentUser.getUserContacts().getContacts().get(x).getLoginID().equals(str)) {
                        flag = true;
                        break;
                    }
                }
                if(!(flag)) {
                    found = true;
                    currentUser.getUserContacts().getContacts().add(user.getPerson());
                    display = user.getPerson();
                } else {
                    System.out.println("User is a repeat");
                }
            }
        }
        if (found) {
            System.out.println("Added " + display.getFirstName() + " " + display.getLastName() +
                    " to your contacts!");
        } else {
            System.out.println("User not found. Unable to add to your contacts.");
        }
        System.out.println("--------------------------------");
    }
    public static void deleteContact(User currentUser, String str) {
        Person display = new Person();
        boolean found = false;
        for (User user : users) {
            if ((str.equals(user.getPerson().getLoginID()))) {
                found = true;
                currentUser.getUserContacts().getContacts().remove(user.getPerson());
                display = user.getPerson();
            }
        }
        if (found) {
            System.out.println("Removed " + display.getFirstName() + " " + display.getLastName() +
                    " from your contacts.");
        } else {
            System.out.println("User not found. Unable to remove from your contacts.");
        }
        System.out.println("--------------------------------");
    }

    public static void conversationManager(User currentUser) {
        Scanner scan = new Scanner(System.in);
        int input;
        String str, subject;
        boolean main = false;
        while(!main) {
            System.out.println("Enter either: ");
            System.out.println("1. Open new conversation");
            System.out.println("2. View all user conversations");
            System.out.println("3. View a conversation");
            System.out.println("4. Send message");
            System.out.println("5. View Archives");
            System.out.println("6. Add archive");
            System.out.println("7. Exit conversation manager");
            input = scan.nextInt();
            System.out.println("---------------------------");
            if (input == 1) {
                System.out.println("Enter the login ID of the person you want to start a conversation with: ");
                str = scan.nextLine();
                System.out.println();
                str = scan.nextLine();
                System.out.println("Enter the subject of the conversation: ");
                subject = scan.nextLine();
                createConversation(currentUser, str, subject);
            } else if (input == 2) {
                //Displays all of currentUsers conversations
                viewUserConversations(currentUser);
                System.out.println("--------------------------------------------");
            } else if (input == 3) {
                System.out.println("Enter the conversation ID of the conversation you would like to view: ");
                String convoID = scan.nextLine();
                System.out.println();
                convoID = scan.nextLine();
                viewConversation(currentUser, convoID);
                System.out.println("--------------------------------------------");
            } else if (input == 4) {
                System.out.println("Enter the conversation ID of the conversation you would like to send messages to: ");
                String convoID = scan.nextLine();
                System.out.println();
                convoID = scan.nextLine();
                sendMessages(currentUser,convoID);
                System.out.println("--------------------------------------------");
            } else if (input == 5) {
                System.out.println("Enter the conversation ID of the conversation archives: ");
                String convoID = scan.nextLine();
                System.out.println();
                convoID = scan.nextLine();
                viewArchives(currentUser, convoID);
                System.out.println("--------------------------------------------");
            } else if(input == 6) {
                System.out.println("Enter the conversation ID of the conversation you would like to archive: ");
                String convoID = scan.nextLine();
                System.out.println();
                convoID = scan.nextLine();
                archiveMessages(currentUser,convoID);
            } else if (input == 7) {
                main = true;
            } else {
                System.out.println("Enter valid option.");
            }
        }
    }
    //createConversation creates a conversation for current user AND ALSO the contact provided
    public static void createConversation(User currentUser, String contactUID, String subject) {
        boolean flag = false;
        String conversationID;
        //Runs through every contact in current user trying to find a match
        for(Person contact : currentUser.getUserContacts().getContacts()) {
            //Creates a conversation if the loginID's match
            if (contact.getLoginID().equals(contactUID)) {
                flag = true;
                conversationID = generateConvoID();
                //Creates conversation for user
                currentUser.getUserConversations().add(new Conversation(currentUser.getPerson(),
                        contact,subject, conversationID));
                //Creates conversation for the other person as well
                //They have the same conversation ID numbers
                for(User user : users) {
                    if (contact.equals(user.getPerson())) {
                        user.getUserConversations().add(new Conversation(contact, currentUser.getPerson(),subject, conversationID));
                    }
                }
                System.out.println("Conversation created between " +
                        currentUser.getPerson().getFirstName() + " " + currentUser.getPerson().getLastName() + " and " +
                        contact.getFirstName() + " " + contact.getLastName());
            }
        }
        if (!flag) {
            System.out.println("Contact not found! Try again.");
        }
        System.out.println("---------------------------");
    }
    public static String generateConvoID() {
        String conversationID = null;
        boolean IDchecker = false;
        while(!IDchecker) {
            Random rand = new Random();
            int idNum = rand.nextInt(10000);
            conversationID = String.valueOf(idNum);
            //The length of conversationID is always 4
            if(conversationID.length() == 1) {
                conversationID = ("000" + conversationID);
            } else if(conversationID.length() == 2) {
                conversationID = ("00" + conversationID);
            } else if(conversationID.length() == 3) {
                conversationID = ("0" + conversationID);
            }
            for (User user : users) {
                for(Conversation convo : user.getUserConversations()) {
                    if ((convo.getConversationID().equals(conversationID))) {
                        IDchecker = true;
                        break;
                    }
                }
            }
            //If IDchecker finds an identical conversation ID number, it will restart
            IDchecker = !IDchecker;
        }
        return conversationID;
    }
    //To view all the conversations in the current user
    public static void viewUserConversations(User currentUser) {
        System.out.println("Here are your current conversations: ");
        for(Conversation convo : currentUser.getUserConversations()) {
            System.out.println("Conversation [#"+ convo.getConversationID() + "] with " +
                    convo.getReceiver().getFirstName() + " about: " + convo.getSubject());
        }
    }
    //To view the messages in a conversation
    public static void viewConversation(User currentUser, String convoID) {
        boolean exists = false;
        for(Conversation convo : currentUser.getUserConversations()) {
            if (convo.getConversationID().equals(convoID)) {
                exists = true;
                System.out.println(convo.getSubject());
                if(convo.getConversation().size() > 0) {
                    for (Message mssg : convo.getConversation()) {
                        System.out.println(mssg.getSender().getFirstName() +"[" + mssg.getSender().getLoginID() + "]: " + mssg.getContent() + "\n" + mssg.getTimeSent());
                    }
                    break;
                } else {
                    System.out.println("There are no messages in this conversation");
                }
            }
        }
        if(!exists) {
            System.out.println("Conversation not found(Invalid conversation ID)");
        }
    }
    public static void sendMessages(User currentUser, String convoID) {
        Scanner scan = new Scanner(System.in);
        String input, login = null;
        StringBuilder inputMod = new StringBuilder();
        int position = 0;
        boolean found, exists = false, archive;
        for (Conversation convo : currentUser.getUserConversations()) {
            if (convo.getConversationID().equals(convoID)) {
                exists = true;
                break;
            }
            position++;
        }
        if(exists) {
            System.out.println("Type out messages to " + currentUser.getUserConversations().get(position).getReceiver().getFirstName() + " "
                    + currentUser.getUserConversations().get(position).getReceiver().getLastName() + ":");
            System.out.println("Enter '*' at the beginning of the message to archive.");
            System.out.println("Press the enter key (and nothing else) to quit.");
            input = scan.nextLine();
            inputMod.append(input);
            while (!input.equals("")) {
                inputMod.replace(0,inputMod.length(),input);
                archive = false;
                if(input.charAt(0) == '*') {
                    inputMod.deleteCharAt(0);
                    input = inputMod.toString();
                    archive = true;
                }
                currentUser.getUserConversations().get(position).addMessage(new Message(currentUser.getUserConversations().get(position).getSender(),
                        currentUser.getUserConversations().get(position).getReceiver(),
                        currentUser.getUserConversations().get(position).getSubject(), input));
                //Really complicated way to add the recent message to the archive
                //Doesn't archive it for other person. May not want it
                if(archive) {
                    currentUser.getUserConversations().get(position).addArchive(currentUser.getUserConversations().get(position).getConversation().get(currentUser.getUserConversations().get(position).getConversation().size()-1));
                }
                //Deletes message if the messages reach more than 10
                if(currentUser.getUserConversations().get(position).getConversation().size() > 10) {
                    currentUser.getUserConversations().get(position).deleteMessage(0);
                }
                //Add the message to the other persons conversation
                login = currentUser.getUserConversations().get(position).getReceiver().getLoginID();
                found = false;
                for (User user : users) {
                    if (user.getPerson().getLoginID().equals(login)) {
                        for (Conversation convo : user.getUserConversations()) {
                            if (convo.getConversationID().equals(convoID)) {
                                convo.addMessage(new Message(currentUser.getUserConversations().get(position).getSender(),
                                        currentUser.getUserConversations().get(position).getReceiver(),
                                        currentUser.getUserConversations().get(position).getSubject(), input));
                                //Deletes message if the messages reach more than 10
                                if(convo.getConversation().size() > 10) {
                                    convo.deleteMessage(0);
                                }
                                found = true;
                                break;

                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                }
                input = scan.nextLine();
            }
        } else {
            System.out.println("This conversation does not exist!");
        }
    }

    public static void viewArchives(User currentUser, String convoID) {
        boolean exists = false;
        for(Conversation convo : currentUser.getUserConversations()) {
            if (convo.getConversationID().equals(convoID)) {
                exists = true;
                System.out.println("Conversation[#" + convo.getConversationID()+ "] Archives: ");
                if(convo.getArchive().size() > 0) {
                    for (Message mssg : convo.getArchive()) {
                        System.out.println(mssg.getSender().getFirstName() +"[" + mssg.getSender().getLoginID() + "]: " + mssg.getContent() + "\n" + mssg.getTimeSent());
                    }
                    break;
                } else {
                    System.out.println("There are no archives in this conversation");
                }
            }
        }
        if(!exists) {
            System.out.println("Conversation not found(Invalid conversation ID)");
        }
    }
    public static void archiveMessages(User currentUser, String convoID) {
        Scanner scan = new Scanner(System.in);
        //Copied code over from viewConversation
        int count = 0, input = -1, position = 0;
        boolean exists = false;
        for(Conversation convo : currentUser.getUserConversations()) {
            if (convo.getConversationID().equals(convoID)) {
                exists = true;
                System.out.println(convo.getSubject());
                if(convo.getConversation().size() > 0) {
                    for (Message mssg : convo.getConversation()) {
                        System.out.println(count + "." + mssg.getSender().getFirstName() +"[" + mssg.getSender().getLoginID() + "]: " + mssg.getContent() + "\n\t" + mssg.getTimeSent());
                        count++;
                    }
                } else {
                    System.out.println("There are no messages in this conversation");
                }
                break;
            }
            position++;
        }
        if(!exists) {
            System.out.println("Conversation not found(Invalid conversation ID)");
        } else {
            boolean found = false;
            int num = 0;
            System.out.println("Enter the message number that you would like to save: ");
            input = scan.nextInt();
            for(Message msg : currentUser.getUserConversations().get(position).getConversation()) {
                if(input == num) {
                    currentUser.getUserConversations().get(position).addArchive(msg);
                    found = true;
                }
                num++;
            }
            if(!found) {
                System.out.println("Message number not found. Not added to archives.");
            } else {
                System.out.println("Message saved to conversations archives!");
            }
        }
    }

    public static void Upload(File file) throws IOException{
        FileOutputStream outStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
        objectOutputFile.writeObject(users);
        objectOutputFile.close();
        outStream.close();
    }
    public static void Download(File file) throws IOException, ClassNotFoundException {
        FileInputStream inStream = new FileInputStream(file);
        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
        users = (ArrayList<User>) objectInputFile.readObject();
        objectInputFile.close();
        inStream.close();
    }

}

/*
while(!main) {
            stop = false;
            while(!stop) {
                System.out.println("--------------------------------------------\nWelcome to the login process.");
                System.out.println("Would you like to create a new account?");
                System.out.println("'Y' or 'N'");
                input = scan.nextLine();
                System.out.println("--------------------------------------------");
                if (input.equals("Y")) {
                    //The users array will add a new user
                    //Accesses the createUser method below main(Line 106)
                    users.add(createUser());
                    System.out.println("Would you like to login as this user? ");
                    System.out.println("'Y' or 'N'");
                    stop2 = false;
                    while(!stop2) {
                        input = scan.nextLine();
                        if(input.equals("Y")) {
                            //This sets the currentUser to the most recently added user
                            currentUser = users.get(users.size()-1);
                            stop = true;
                            stop2 = true;
                            System.out.println("--------------------------------------------");
                        } else if (input.equals("N")) {
                            System.out.println("Continue the login process.");
                            stop2 = true;
                        } else {
                            System.out.println("Please input either 'Y' or 'N'");
                        }
                    }

                } else if(input.equals("N")){
                    System.out.println("Are you a returning user?");
                    System.out.println("'Y' or 'N'");
                    input = scan.nextLine();
                    if (input.equals("Y")) {
                        System.out.println("--------------------------------------------");
                        //login from known users
                        //Access the logIn method below main (Line 176)
                        currentUser = logIn();
                        stop = true;
                    } else if(input.equals("N")) {
                        System.out.println("You must create a new account or log in from an existing one to continue.");
                    } else {
                        System.out.println("Enter 'Y' or 'N' to continue.");
                    }
                } else {
                    System.out.println("Enter 'Y' or 'N' to continue.");
                }
            }
            //Will only continue if the currentUser is logged in
            if(!(currentUser == null)){
                main = true;
            }
        }
 */