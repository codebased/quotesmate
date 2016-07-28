package model.mail;

/**
 * Created by codebased on 14/01/16.
 */
public class EmailAddress {
    private String name;
    private String address;


    public EmailAddress() { }
    public EmailAddress(String name, String address){
        this.name = name;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
