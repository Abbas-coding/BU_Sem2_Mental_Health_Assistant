package source.mentalhealthassistant.core;

import java.util.ArrayList;

public class SupportNetwork {
    private ArrayList<Contact> contactList;

    public SupportNetwork() {
        this.contactList = new ArrayList<Contact>();
    }
    public void addContact(Contact contact) {
        //contactList.add(contact);
    }
    public void removeContact(Contact contact) {
        contactList.remove(contact);
    }
    public ArrayList<Contact> getContactList() {
        return contactList;
    }
}
