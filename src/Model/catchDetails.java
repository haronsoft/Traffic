package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class catchDetails {

//    Properties
    private final StringProperty title;
    private final StringProperty fistName;
    private final StringProperty lastName;
    private final StringProperty area;
    private final StringProperty contacts;

    //Default Constructor
    public catchDetails(String title, String fistName, String lastName, String area, String contacts) {
        this.fistName = new SimpleStringProperty(fistName);
        this.lastName = new SimpleStringProperty(lastName);
        this.area = new SimpleStringProperty(area);
        this.contacts = new SimpleStringProperty(contacts);
        this.title = new SimpleStringProperty(title);

    }

    //Getters
    public String gettitle() {
        return title.get();
    }

    public String getFistName() {
        return fistName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getArea() {
        return area.get();
    }

    public String getContacts() {
        return contacts.get();
    }

    //Setters
    public void settitle(String value) {
        title.set(value);
    }

    public void setfirstName(String value) {
        fistName.set(value);
    }

    public void setlastName(String value) {
        lastName.set(value);
    }

    public void setArea(String value) {
        area.set(value);
    }

    //Property Values
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty firstNameProperty() {
        return fistName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty areaProperty() {
        return area;
    }

    public StringProperty contactProperty() {
        return contacts;
    }

}
