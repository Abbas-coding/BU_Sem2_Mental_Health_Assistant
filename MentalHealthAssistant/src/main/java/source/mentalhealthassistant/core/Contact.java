package source.mentalhealthassistant.core;

public class Contact {
    private String contactId;
    private String name;
    private String relationship;
    private String phoneNumber;

    public Contact(String contactId, String name, String relationship, String phoneNumber) {
        this.contactId = contactId;
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
    }
    public void updateContactInfo(){
        //TODO
    }
}
