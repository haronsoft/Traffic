package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminCatchDetails {

//    Properties
    private final StringProperty title;
    private final StringProperty fistName;
    private final StringProperty lastName;
   
    private final StringProperty contacts;
    private final StringProperty gender;
    private final StringProperty station;
    private final StringProperty day;
    private final StringProperty id;

    //Default Constructor
    public AdminCatchDetails(String id, String title, String fistName, String lastName, String contacts, String gender, String station, String day) {
        this.id = new SimpleStringProperty(id);
        this.fistName = new SimpleStringProperty(fistName);
        this.lastName = new SimpleStringProperty(lastName);
        this.contacts = new SimpleStringProperty(contacts);
        this.title = new SimpleStringProperty(title);
        this.gender = new SimpleStringProperty(gender);
        this.station = new SimpleStringProperty(station);
        this.day = new SimpleStringProperty(day);

    }

    //Getters
    public String getId() {
        return id.get();
    }

    public String gettitle() {
        return title.get();
    }

    public String getFistName() {
        return fistName.get();
    }

    public String getLastName() {
        return lastName.get();
    }


    public String getContacts() {
        return contacts.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getStation() {
        return station.get();
    }

    public String getDay() {
        return day.get();
    }

    //Setters
    public void setId(String value) {
        id.set(value);
    }

    public void settitle(String value) {
        title.set(value);
    }

    public void setfirstName(String value) {
        fistName.set(value);
    }

    public void setlastName(String value) {
        lastName.set(value);
    }

  

    public void setContacts(String value) {
        contacts.set(value);
    }

    public void setGendar(String value) {
        gender.set(value);
    }

    public void setStation(String value) {
        station.set(value);
    }

    public void setDay(String value) {
        day.set(value);
    }

    //Property Values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty firstNameProperty() {
        return fistName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }


    public StringProperty contactProperty() {
        return contacts;
    }

    public StringProperty genderStringProperty() {
        return gender;
    }

    public StringProperty stationStringProperty() {
        return station;
    }

    public StringProperty dayStringProperty() {
        return day;
    }

}

////////////////////////////////////////////////
////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////
